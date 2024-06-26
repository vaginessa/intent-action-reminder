    package com.intentaction.reminder.data.dao

    import androidx.lifecycle.LiveData
    import androidx.room.Dao
    import androidx.room.Delete
    import androidx.room.Insert
    import androidx.room.OnConflictStrategy
    import androidx.room.Query
    import androidx.room.Update
    import com.intentaction.reminder.data.entity.IntentAction

    @Dao
    interface ActionIntentDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertIntent(intentAction: IntentAction): Long

        @Update
        suspend fun updateIntent(intentAction: IntentAction)

        @Delete
        suspend fun deleteIntent(intentAction: IntentAction)

        @Query("SELECT * FROM intent_actions")
        fun getAllIntents(): LiveData<List<IntentAction>>

        // get all intents with a specific status
        @Query("SELECT * FROM intent_actions WHERE status = :status")
        fun getIntentsByStatus(status: String): LiveData<List<IntentAction>>

        // get all intents with a specific category
        @Query("SELECT * FROM intent_actions WHERE category = :category")
        fun getIntentsByCategory(category: String): LiveData<List<IntentAction>>

        // get all intents with a specific status and category
        @Query("SELECT * FROM intent_actions WHERE status = :status AND category = :category")
        fun getIntentsByStatusAndCategory(status: String, category: String): LiveData<List<IntentAction>>

        // get intent by id
        @Query("SELECT * FROM intent_actions WHERE id = :id")
        fun getIntentById(id: Int): IntentAction?

        // get intent in a specific date range
        @Query("SELECT * FROM intent_actions WHERE dueDate BETWEEN :startDate AND :endDate")
        fun getIntentsInDateRange(startDate: Long, endDate: Long): LiveData<List<IntentAction>>

        // get all intents with a specific status and in a specific date range
        @Query("SELECT * FROM intent_actions WHERE status = :status AND dueDate BETWEEN :startDate AND :endDate")
        fun getIntentsByStatusInDateRange(status: String, startDate: Long, endDate: Long): LiveData<List<IntentAction>>


        // get all the unfulfilled intents
        @Query("SELECT * FROM intent_actions WHERE status != 'fulfilled'")
        suspend fun getUnfulfilledIntents(): List<IntentAction>

    }
