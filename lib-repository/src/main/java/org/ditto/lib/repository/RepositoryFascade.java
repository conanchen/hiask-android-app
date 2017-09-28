package org.ditto.lib.repository;

import org.ditto.lib.apirest.ApirestFascade;

import javax.inject.Inject;

/**
 * Created by amirziarati on 10/4/16.
 */
public class RepositoryFascade {


    @Inject
    String strAmir;

    public BuyanswerRepository buyanswerRepository;
    public  IndexMessageRepository indexMessageRepository;
    public  IndexPartyRepository indexPartyRepository;
    public  IndexBuyanswerRepository indexBuyanswerRepository;
    public  UserRepository userRepository;
    public  ApirestFascade apirestFascade;
    public  GiftRepository giftRepository;
    public  ProfessionRepository professionRepository;


    @Inject
    public RepositoryFascade(UserRepository userRepository,
                             BuyanswerRepository buyanswerRepository,
                             IndexMessageRepository indexMessageRepository,
                             IndexPartyRepository indexPartyRepository,
                             IndexBuyanswerRepository indexBuyanswerRepository,
                             GiftRepository giftRepository,
                             ProfessionRepository professionRepository,
                             ApirestFascade apirestFascade ) {
        this.userRepository = userRepository;
        this.buyanswerRepository = buyanswerRepository;
        this.indexMessageRepository = indexMessageRepository;
        this.indexPartyRepository = indexPartyRepository;
        this.indexBuyanswerRepository = indexBuyanswerRepository;
        this.giftRepository = giftRepository;
        this.professionRepository = professionRepository;
        this.apirestFascade = apirestFascade;
        System.out.println(strAmir);

    }

    public String getConvertedStrAmir() {
        return "Convert " + strAmir;
    }

}