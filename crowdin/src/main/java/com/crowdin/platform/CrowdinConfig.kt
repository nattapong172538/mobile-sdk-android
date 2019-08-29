package com.crowdin.platform

import com.crowdin.platform.data.model.AuthConfig
import com.crowdin.platform.data.remote.NetworkType

/**
 * Contains configuration properties for initializing Crowdin.
 */
class CrowdinConfig private constructor() {

    var isPersist: Boolean = true
    var distributionHash: String = ""
    var filePaths: Array<out String>? = null
    var networkType: NetworkType = NetworkType.ALL
    var isRealTimeUpdateEnabled: Boolean = false
    var isScreenshotEnabled: Boolean = false
    var updateInterval: Long = -1
    var sourceLanguage: String = ""
    var authConfig: AuthConfig? = null

    class Builder {

        private var isPersist: Boolean = true
        private var distributionHash: String = ""
        private var filePaths: Array<out String>? = null
        private var networkType: NetworkType = NetworkType.ALL
        private var isRealTimeUpdateEnabled: Boolean = false
        private var isScreenshotEnabled: Boolean = false
        private var updateInterval: Long = -1
        private var sourceLanguage: String = ""
        private var authConfig: AuthConfig? = null

        fun persist(isPersist: Boolean): Builder {
            this.isPersist = isPersist
            return this
        }

        fun withDistributionHash(distributionHash: String): Builder {
            this.distributionHash = distributionHash
            return this
        }

        fun withFilePaths(vararg filePaths: String): Builder {
            this.filePaths = filePaths
            return this
        }

        fun withNetworkType(networkType: NetworkType): Builder {
            this.networkType = networkType
            return this
        }

        fun withRealTimeUpdates(): Builder {
            this.isRealTimeUpdateEnabled = true
            return this
        }

        fun withScreenshotEnabled(): Builder {
            this.isScreenshotEnabled = true
            return this
        }

        fun withSourceLanguage(sourceLanguage: String): Builder {
            this.sourceLanguage = sourceLanguage
            return this
        }

        fun withUpdateInterval(updateInterval: Long): Builder {
            this.updateInterval = updateInterval
            return this
        }

        fun withAuthConfig(authConfig: AuthConfig): Builder {
            this.authConfig = authConfig
            return this
        }

        fun build(): CrowdinConfig {
            val config = CrowdinConfig()
            config.isPersist = isPersist
            require(distributionHash.isNotEmpty()) { "Crowdin: `distributionHash` cannot be empty" }

            config.distributionHash = distributionHash

            requireNotNull(filePaths) { "Crowdin: `filePaths` cannot be null" }

            config.filePaths = filePaths
            config.networkType = networkType
            config.isRealTimeUpdateEnabled = isRealTimeUpdateEnabled
            config.isScreenshotEnabled = isScreenshotEnabled

            require(!((isRealTimeUpdateEnabled || isScreenshotEnabled) && sourceLanguage.isEmpty())) {
                "Crowdin: `sourceLanguage` cannot be empty"
            }

            config.sourceLanguage = sourceLanguage
            config.updateInterval = updateInterval
            config.authConfig = authConfig

            return config
        }
    }
}