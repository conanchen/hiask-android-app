package org.ditto.lib.apirest;


import javax.inject.Inject;

/**
 * Created by amirziarati on 10/4/16.
 */
public class ApirestFascade {

    @Inject
    String strAmir;

    GithubService githubService;
    RxGithubService rxGithubService;

    @Inject
    public ApirestFascade(GithubService githubService, RxGithubService rxGithubService) {
        this.githubService = githubService;
        this.rxGithubService = rxGithubService;
        System.out.println(strAmir);

    }


    public String getConvertedStrAmir() {
        return "Convert " + strAmir;
    }

    public GithubService getGithubService() {
        return githubService;
    }

    public RxGithubService getRxGithubService() {
        return rxGithubService;
    }
}