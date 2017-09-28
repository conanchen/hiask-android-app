package org.ditto.lib.repository.di;

import org.ditto.lib.apirest.ApirestFascade;
import org.ditto.lib.apirest.di.ApirestModule;
import org.ditto.lib.dbroom.RoomFascade;
import org.ditto.lib.dbroom.di.RoomModule;
import org.ditto.lib.repository.BuyanswerRepository;
import org.ditto.lib.repository.GiftRepository;
import org.ditto.lib.repository.IndexBuyanswerRepository;
import org.ditto.lib.repository.IndexMessageRepository;
import org.ditto.lib.repository.IndexPartyRepository;
import org.ditto.lib.repository.ProfessionRepository;
import org.ditto.lib.repository.RepositoryFascade;
import org.ditto.lib.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by amirziarati on 10/4/16.
 */
@Singleton
@Module(includes = {
        RoomModule.class,
        ApirestModule.class
})
public class RepositoryModule {

    @Singleton
    @Provides
    public RepositoryFascade provideRepositoryFascade(
            UserRepository userRepository,
            BuyanswerRepository buyanswerRepository,
            IndexMessageRepository indexMessageRepository,
            IndexPartyRepository indexPartyRepository,
            IndexBuyanswerRepository indexBuyanswerRepository,
            GiftRepository giftRepository,
            ProfessionRepository professionRepository,
            ApirestFascade apirestFascade
    ) {
        return new RepositoryFascade(userRepository,
                buyanswerRepository,
                indexMessageRepository,
                indexPartyRepository,
                indexBuyanswerRepository,
                giftRepository,
                professionRepository,
                apirestFascade);
    }


    @Singleton
    @Provides
    public UserRepository provideUserRepository(
            ApirestFascade apirestFascade,
            RoomFascade roomFascade) {
        return new UserRepository(apirestFascade, roomFascade);
    }


    @Singleton
    @Provides
    public BuyanswerRepository provideBuyanswerRepository(
            ApirestFascade apirestFascade,
            RoomFascade roomFascade) {
        return new BuyanswerRepository(apirestFascade, roomFascade);
    }


    @Singleton
    @Provides
    public IndexMessageRepository provideMessageIndexRepository(
            ApirestFascade apirestFascade,
            RoomFascade roomFascade) {
        return new IndexMessageRepository(apirestFascade, roomFascade);
    }


    @Singleton
    @Provides
    public IndexPartyRepository providePartyIndexRepository(
            ApirestFascade apirestFascade,
            RoomFascade roomFascade) {
        return new IndexPartyRepository(apirestFascade, roomFascade);
    }


    @Singleton
    @Provides
    public IndexBuyanswerRepository provideIndexBuyanswerRepository(
            ApirestFascade apirestFascade,
            RoomFascade roomFascade) {
        return new IndexBuyanswerRepository(apirestFascade, roomFascade);
    }

    @Singleton
    @Provides
    public GiftRepository provideGiftRepository(
            ApirestFascade apirestFascade,
            RoomFascade roomFascade) {
        return new GiftRepository(apirestFascade, roomFascade);
    }

    @Singleton
    @Provides
    public ProfessionRepository provideProfessionRepository(
            ApirestFascade apirestFascade,
            RoomFascade roomFascade) {
        return new ProfessionRepository(apirestFascade, roomFascade);
    }

}