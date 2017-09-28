package org.ditto.feature.buyanswer.chat;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.Typed2EpoxyController;

import org.ditto.feature.base.content.models.ChatContentTextModel_;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.buyanswer.BuyanswerMessage;

import java.util.List;

public class BuyanswerChatController extends Typed2EpoxyController<List<BuyanswerMessage>, List<BuyanswerCommand>> {
    public interface AdapterCallbacks {

        void onMessageItemClicked(BuyanswerMessage carousel, int colorPosition);

        void onMessageCommandItemClicked(BuyanswerCommand message, int position);
    }


    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    public BuyanswerChatController(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(List<BuyanswerMessage> messages, List<BuyanswerCommand> upsertMessageCommands) {
        addMessages(messages);
        addUpsertMessageCommands(upsertMessageCommands);
    }

    private void addMessages(List<BuyanswerMessage> messages) {
        if (messages != null) {
            for (BuyanswerMessage message : messages) {
                add(new ChatContentTextModel_()
                        .id(message.uuid)
                        .detail(message.content)
                        .clickListener((model, parentView, clickedView, position) -> {
                            callbacks.onMessageItemClicked(message, position);
                        }));
            }
        }
    }

    private void addUpsertMessageCommands(List<BuyanswerCommand> upsertMessageCommands) {
        if (upsertMessageCommands != null) {
            for (BuyanswerCommand command : upsertMessageCommands) {
                add(new ChatContentTextModel_()
                        .id(command.uuid)
                        .detail(command.content.toString())
                        .clickListener((model, parentView, clickedView, position) -> {
                            callbacks.onMessageCommandItemClicked(command, position);
                        }));
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
