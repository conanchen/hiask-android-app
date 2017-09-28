package org.ditto.hiask.todo;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed2EpoxyController;


import org.ditto.feature.buyanswer.create.epoxymodels.ItemCommandCreateBuyanswerModel_;
import org.ditto.hiask.todo.model.ArticleTypeData;
import org.ditto.hiask.todo.model.ItemTypeBuyanswerModel_;
import org.ditto.hiask.todo.model.ItemTypeBuyreadModel_;
import org.ditto.hiask.todo.model.ItemTypeSellreadModel_;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;

import java.util.List;


public class TodoController extends Typed2EpoxyController<List<BuyanswerCommand>, List<ArticleTypeData>> {
    public interface AdapterCallbacks {

        void onArticleBuyanswerClicked(ArticleTypeData carousel, int colorPosition);

        void onArticleBuyreadClicked(ArticleTypeData carousel, int colorPosition);


        void onArticleSellreadClicked(ArticleTypeData carousel, int colorPosition);

        void onCreateCommandBuyanswerClicked(BuyanswerCommand buyanswerCommand, int position);
    }

    @AutoModel
    ItemTypeBuyanswerModel_ buyanswerModel_;

    @AutoModel
    ItemTypeBuyreadModel_ buyreadModel_;

    @AutoModel
    ItemTypeSellreadModel_ sellreadModel_;


    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    public TodoController(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(List<BuyanswerCommand> buyanswerCreateCommands, List<ArticleTypeData> carousels) {
        addArticleTypes(carousels);
        addBuyanswerCreateCommands(buyanswerCreateCommands);
    }

    private void addBuyanswerCreateCommands(List<BuyanswerCommand> buyanswerCreateCommands) {
        if (buyanswerCreateCommands != null) {
            for (BuyanswerCommand command : buyanswerCreateCommands) {
                new ItemCommandCreateBuyanswerModel_()
                        .id(command.uuid)
                        .title(command.content == null || command.content.create == null
                                ? "TODO: create title buyanswer"
                                : command.content.create.title)
                        .clickListener((model, parentView, clickedView, position) -> {
                            callbacks.onCreateCommandBuyanswerClicked(command, position);
                        })
                        .addTo(this);
            }
        }
    }

    private void addArticleTypes(List<ArticleTypeData> carousels) {
        //----------
        if (carousels != null) {
            for (ArticleTypeData articleTypeData : carousels) {
                if (ArticleTypeData.TYPE.BUYANSWER.equals(articleTypeData.getType())) {
                    buyanswerModel_.title(articleTypeData.getTitle())
                            .detail(articleTypeData.getDetail())
                            .clickListener((model, parentView, clickedView, position) -> {
                                callbacks.onArticleBuyanswerClicked(articleTypeData, position);
                            }).addTo(this);
                } else if (ArticleTypeData.TYPE.BUYREAD.equals(articleTypeData.getType())) {
                    buyreadModel_.title(articleTypeData.getTitle())
                            .detail(articleTypeData.getDetail())
                            .clickListener((model, parentView, clickedView, position) -> {
                                callbacks.onArticleBuyreadClicked(articleTypeData, position);
                            })
                            .addTo(this);
                } else if (ArticleTypeData.TYPE.SELLREAD.equals(articleTypeData.getType())) {
                    sellreadModel_.title(articleTypeData.getTitle())
                            .detail(articleTypeData.getDetail())
                            .clickListener((model, parentView, clickedView, position) -> {
                                callbacks.onArticleSellreadClicked(articleTypeData, position);
                            })
                            .addTo(this);
                }
            }
        }
    }

    @Override
    protected void onExceptionSwallowed(RuntimeException exception) {
        // Best practice is to throw in debug so you are aware of any issues that Epoxy notices.
        // Otherwise Epoxy does its best to swallow these exceptions and continue gracefully
        throw exception;
    }
}
