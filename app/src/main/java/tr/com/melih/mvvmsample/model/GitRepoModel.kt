package tr.com.melih.mvvmsample.model

import tr.com.melih.mvvmsample.manager.NetManager
import tr.com.melih.mvvmsample.model.repository.GitRepoLocalDataSource
import tr.com.melih.mvvmsample.model.repository.GitRepoRemoteDataSource
import tr.com.melih.mvvmsample.model.repository.OnRepoLocalReadyCallback
import tr.com.melih.mvvmsample.model.repository.OnRepoRemoteReadyCallback

/**
* Created by melih on 24/09/2017.
*/
class GitRepoModel(private val netManager: NetManager) {
    private val localDataSource = GitRepoLocalDataSource()
    private val remoteDataSource = GitRepoRemoteDataSource()

    fun getRepositories(onRepositoryReadyCallback: OnRepositoryReadyCallback) {
        netManager.isConnectedToInternet?.let {
            if (it) {
                remoteDataSource.getRepositories(object : OnRepoRemoteReadyCallback {
                    override fun onRemoteDataReady(data: ArrayList<Repository>) {
                        localDataSource.saveRepositories(data)
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            } else {
                localDataSource.getRepositories(object : OnRepoLocalReadyCallback {
                    override fun onLocalDataReady(data: ArrayList<Repository>) {
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            }
        }
    }
}

data class Repository(val repositoryName:String, val repositoryOwner:String, val numberOfStars:Int, val hasIssues:Boolean)

interface OnRepositoryReadyCallback {
    fun onDataReady(data : ArrayList<Repository>)
}