package com.example.notaviva.data;

/**
 * Capa de repositorio: aísla al ViewModel de los detalles de Room.
 * Facilita además las pruebas unitarias mediante inyección de dependencias.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0012\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00180\u0017J\u0016\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00172\u0006\u0010\u001a\u001a\u00020\u0006J\u001a\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00180\u00172\u0006\u0010\u001c\u001a\u00020\u0006J\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00180\u00172\u0006\u0010\u001c\u001a\u00020\u0006J\u0016\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010 \u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/notaviva/data/CaseRepository;", "", "db", "Lcom/example/notaviva/data/AppDatabase;", "(Lcom/example/notaviva/data/AppDatabase;)V", "addEvidence", "", "evidence", "Lcom/example/notaviva/data/EvidenceEntity;", "(Lcom/example/notaviva/data/EvidenceEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addInterview", "interview", "Lcom/example/notaviva/data/InterviewEntity;", "(Lcom/example/notaviva/data/InterviewEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createCase", "case", "Lcom/example/notaviva/data/CaseEntity;", "(Lcom/example/notaviva/data/CaseEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCase", "", "deleteEvidence", "deleteInterview", "getAllCases", "Lkotlinx/coroutines/flow/Flow;", "", "getCaseById", "id", "getEvidences", "caseId", "getInterviews", "updateCase", "updateEvidence", "updateInterview", "app_debug"})
public final class CaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.notaviva.data.AppDatabase db = null;
    
    public CaseRepository(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.AppDatabase db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.notaviva.data.CaseEntity>> getAllCases() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.example.notaviva.data.CaseEntity> getCaseById(long id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createCase(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.CaseEntity p0_1523096, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateCase(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.CaseEntity p0_1523096, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteCase(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.CaseEntity p0_1523096, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.notaviva.data.InterviewEntity>> getInterviews(long caseId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addInterview(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.InterviewEntity interview, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateInterview(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.InterviewEntity interview, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteInterview(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.InterviewEntity interview, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.notaviva.data.EvidenceEntity>> getEvidences(long caseId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addEvidence(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.EvidenceEntity evidence, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateEvidence(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.EvidenceEntity evidence, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteEvidence(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.EvidenceEntity evidence, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}