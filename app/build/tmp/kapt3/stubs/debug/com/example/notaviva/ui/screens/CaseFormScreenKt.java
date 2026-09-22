package com.example.notaviva.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001aA\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\tH\u0007\u00a2\u0006\u0002\u0010\n\u001a\b\u0010\u000b\u001a\u00020\fH\u0002\u00a8\u0006\r"}, d2 = {"CaseFormScreen", "", "viewModel", "Lcom/example/notaviva/viewmodel/CaseViewModel;", "caseId", "", "onBack", "Lkotlin/Function0;", "onSaved", "Lkotlin/Function1;", "(Lcom/example/notaviva/viewmodel/CaseViewModel;Ljava/lang/Long;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "defaultDate", "", "app_debug"})
public final class CaseFormScreenKt {
    
    /**
     * Pantalla única para crear o editar un caso.
     * Si [caseId] es null se trata de una creación; si no, se cargan y actualizan
     * los datos del caso existente.
     */
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void CaseFormScreen(@org.jetbrains.annotations.NotNull()
    com.example.notaviva.viewmodel.CaseViewModel viewModel, @org.jetbrains.annotations.Nullable()
    java.lang.Long caseId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onSaved) {
    }
    
    private static final java.lang.String defaultDate() {
        return null;
    }
}