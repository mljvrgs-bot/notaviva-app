package com.example.notaviva.data;

/**
 * Estados posibles de un caso periodístico.
 * Se guarda en BD como [name] y se muestra al usuario con [label].
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\u000b"}, d2 = {"Lcom/example/notaviva/data/CaseStatus;", "", "label", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "EN_INVESTIGACION", "PUBLICADO", "CERRADO", "Companion", "app_debug"})
public enum CaseStatus {
    /*public static final*/ EN_INVESTIGACION /* = new EN_INVESTIGACION(null) */,
    /*public static final*/ PUBLICADO /* = new PUBLICADO(null) */,
    /*public static final*/ CERRADO /* = new CERRADO(null) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.notaviva.data.CaseStatus.Companion Companion = null;
    
    CaseStatus(java.lang.String label) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.example.notaviva.data.CaseStatus> getEntries() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/notaviva/data/CaseStatus$Companion;", "", "()V", "fromName", "Lcom/example/notaviva/data/CaseStatus;", "name", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.notaviva.data.CaseStatus fromName(@org.jetbrains.annotations.Nullable()
        java.lang.String name) {
            return null;
        }
    }
}