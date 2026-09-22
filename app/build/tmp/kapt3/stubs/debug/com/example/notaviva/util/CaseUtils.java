package com.example.notaviva.util;

/**
 * Funciones puras de lógica de negocio, separadas de la UI y la persistencia
 * para poder probarlas con JUnit sin necesidad de un dispositivo/emulador.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\bJ\u001c\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010\u000b\u001a\u00020\bJ\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u001e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0012J\u0016\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012J\u001e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0012\u00a8\u0006\u001c"}, d2 = {"Lcom/example/notaviva/util/CaseUtils;", "", "()V", "canChangeStatusTo", "", "case", "Lcom/example/notaviva/data/CaseEntity;", "newStatus", "Lcom/example/notaviva/data/CaseStatus;", "canClose", "canModifyCaseContent", "status", "countByStatus", "", "cases", "", "filterCases", "query", "", "isValidCase", "title", "description", "date", "isValidEvidence", "name", "isValidInterview", "personName", "findings", "app_debug"})
public final class CaseUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.notaviva.util.CaseUtils INSTANCE = null;
    
    private CaseUtils() {
        super();
    }
    
    /**
     * Filtra una lista de casos por título o descripción (case-insensitive).
     * Una cadena en blanco devuelve la lista completa.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.notaviva.data.CaseEntity> filterCases(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.notaviva.data.CaseEntity> cases, @org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    /**
     * Un caso solo se puede publicar/cerrar si tiene al menos una conclusión escrita.
     */
    public final boolean canClose(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.CaseEntity p0_1523096) {
        return false;
    }
    
    /**
     * Valida los campos obligatorios antes de guardar un caso.
     */
    public final boolean isValidCase(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return false;
    }
    
    public final int countByStatus(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.notaviva.data.CaseEntity> cases, @org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.CaseStatus status) {
        return 0;
    }
    
    public final boolean isValidInterview(@org.jetbrains.annotations.NotNull()
    java.lang.String personName, @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String findings) {
        return false;
    }
    
    public final boolean canModifyCaseContent(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.CaseStatus status) {
        return false;
    }
    
    public final boolean canChangeStatusTo(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.CaseEntity p0_1523096, @org.jetbrains.annotations.NotNull()
    com.example.notaviva.data.CaseStatus newStatus) {
        return false;
    }
    
    public final boolean isValidEvidence(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description) {
        return false;
    }
}