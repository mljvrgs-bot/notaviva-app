package com.example.notaviva.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\f\u001a\u00020\rH\'J\u0016\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/example/notaviva/data/InterviewDao;", "", "delete", "", "interview", "Lcom/example/notaviva/data/InterviewEntity;", "(Lcom/example/notaviva/data/InterviewEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWithCase", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/notaviva/data/InterviewWithCase;", "getByCaseId", "caseId", "", "insert", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface InterviewDao {
    
    /**
     * Obtiene las entrevistas pertenecientes
     * a un caso específico.
     *
     * Esta función ya era utilizada por CaseDetailScreen,
     * por eso se conserva.
     */
    @androidx.room.Query(value = "SELECT * FROM interviews WHERE caseId = :caseId ORDER BY id DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.notaviva.data.InterviewEntity>> getByCaseId(long caseId);
    
    /**
     * Obtiene todas las entrevistas junto con
     * el nombre del caso al que pertenecen.
     *
     * Se utiliza principalmente en HomeScreen.
     */
    @androidx.room.Query(value = "\n        SELECT\n            i.id AS id,\n            i.caseId AS caseId,\n            i.personName AS personName,\n            i.date AS date,\n            i.findings AS findings,\n            c.title AS caseTitle\n        FROM interviews i\n        INNER JOIN cases c\n            ON i.caseId = c.id\n        ORDER BY i.id DESC\n        ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.notaviva.data.InterviewWithCase>> getAllWithCase();
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.InterviewEntity interview, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.InterviewEntity interview, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.InterviewEntity interview, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}