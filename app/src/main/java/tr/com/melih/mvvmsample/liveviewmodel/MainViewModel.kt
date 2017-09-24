package tr.com.melih.mvvmsample.liveviewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import tr.com.melih.mvvmsample.manager.NetManager
import tr.com.melih.mvvmsample.model.OnDataReadyCallback
import tr.com.melih.mvvmsample.model.OnRepositoryReadyCallback
import tr.com.melih.mvvmsample.model.GitRepoModel
import tr.com.melih.mvvmsample.model.Repository

/**
 * Created by melih on 24/09/2017.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
//class MainViewModel : AndroidViewModel {
    //constructor(application: Application) : super(application)

    var gitRepoModel: GitRepoModel = GitRepoModel(NetManager(getApplication()))

    var repositories = MutableLiveData<ArrayList<Repository>>()

    val text = ObservableField("old data")

    val isLoading = ObservableField(false)

    fun loadRepositories(){
        isLoading.set(true)
        gitRepoModel.getRepositories(object : OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }
}