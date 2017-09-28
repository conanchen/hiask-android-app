package org.ditto.hiask.di;

import org.ditto.feature.buyanswer.chat.BuyanswerChatActivity;
import org.ditto.feature.buyanswer.edit.BuyanswerEditActivity;
import org.ditto.feature.buyanswer.di.BuyanswerFragmentBuildersModule;
import org.ditto.feature.index.di.IndexFragmentBuildersModule;
import org.ditto.feature.my.di.MyFragmentBuildersModule;
import org.ditto.feature.my.myprofile.edit.ConsultingpriceEditActivity;
import org.ditto.feature.my.myprofile.edit.ProfessionsEditActivity;
import org.ditto.feature.test.di.TestFragmentBuildersModule;
import org.ditto.hiask.todo.TodoActivity;
import org.ditto.hiask.MainActivity;
import org.ditto.lib.usecases.AppServiceCommandSenderImpl;
import org.ditto.lib.usecases.AppServiceKeepliveTraceImpl;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module()
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = {
            IndexFragmentBuildersModule.class,
            MyFragmentBuildersModule.class,
            TestFragmentBuildersModule.class,
            BuyanswerFragmentBuildersModule.class})
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract TodoActivity contributeCreateArticleActivity();

    @ContributesAndroidInjector
    abstract AppServiceKeepliveTraceImpl contributeAppServiceKeepliveTraceImpl();

    @ContributesAndroidInjector
    abstract AppServiceCommandSenderImpl contributeAppServiceCommandSenderImpl();


    @ContributesAndroidInjector(modules = BuyanswerFragmentBuildersModule.class)
    abstract BuyanswerEditActivity contributeBuyanswerEditActivity();


    @ContributesAndroidInjector(modules = BuyanswerFragmentBuildersModule.class)
    abstract BuyanswerChatActivity contributeBuyanswerChatActivity();


    @ContributesAndroidInjector
    abstract ConsultingpriceEditActivity contributeConsultingpriceEditActivity();



    @ContributesAndroidInjector
    abstract ProfessionsEditActivity contributeProfessionsEditActivity();


}