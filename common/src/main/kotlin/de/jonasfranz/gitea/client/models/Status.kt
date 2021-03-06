package de.jonasfranz.gitea.client.models

import de.jonasfranz.gitea.client.utils.JSONDate
import de.jonasfranz.gitea.client.utils.SerializedName
import kotlinx.serialization.Serializable

object Status {
    /**
     * Status holds a single Status of a single commit
     * godoc: https://godoc.org/code.gitea.io/sdk/gitea#Status
     *
     * @author Jonas Franz
     *
     * @param[id] an unique id of the status
     * @param[state] [State] indicator of the given status report
     * @param[creator] the user who created the status
     * @param[description] a short description about the status change
     * @param[url] the api url of the given status
     * @param[targetURL] an url with more information about the status change (e.g. build log)
     * @param[createdAt] the date when the status was created
     * @param[updatedAt] the date when the status got updated the last time
     */
    @Serializable
    data class Status(
            var id: Int,
            @SerializedName("status")
            var state: String,
            @SerializedName("target_url")
            var targetURL: String,
            var description: String,
            var url: String,
            var context: String,
            var creator: User,
            @SerializedName("created_at")
            var createdAt: JSONDate,
            @SerializedName("updated_at")
            var updatedAt: JSONDate
    )

    /**
     * CombinedStatus holds the combined state of several statuses for a single commit
     * godoc: https://godoc.org/code.gitea.io/sdk/gitea#CombinedStatus
     *
     * @author Jonas Franz
     *
     * @param[state] the latest reported [State]
     * @param[sha] commit's SHA hash
     * @param[totalCount] count of the statuses inside of this [CombinedStatus]
     * @param[statuses] list out of the latest [Status] reports
     * @param[repository] commit's repository
     * @param[commitURL] commit's URL (empty when [StatusAPI.getCombinedStatus] is used)
     */
    @Serializable
    data class CombinedStatus(
            var state: String,
            var sha: String,
            @SerializedName("total_count")
            var totalCount: Int,
            var statuses: List<Status>,
            var repository: Repository,
            @SerializedName("commit_url")
            var commitURL: String,
            var url: String
    )

    @Serializable
    data class CreateStatusOption(
            var state: String,
            @SerializedName("target_url")
            var targetURL: String,
            var description: String,
            var context: String
    )
}