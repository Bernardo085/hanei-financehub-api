package com.hanei.financehub.api.domain.model;

import com.hanei.financehub.api.domain.exception.BusinessRuleException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @Test
    void deveRegistrarUsuarioLocalComSenha() {
        User user = User.registerLocal("Bernardo", "Pinheiro", "bernardo@example.com", "+5585999999999", "hash123");

        assertThat(user.getEmail()).isEqualTo("bernardo@example.com");
        assertThat(user.getAuthProvider()).isEqualTo(AuthProvider.LOCAL);
        assertThat(user.getOnboarding().isCompleted()).isFalse();
    }

    @Test
    void deveRecusarRegistroLocalSemSenha() {
        assertThatThrownBy(() -> User.registerLocal("Bernardo", "Pinheiro", "bernardo@example.com", "+55", null))
                .isInstanceOf(BusinessRuleException.class);
    }

    @Test
    void deveRegistrarUsuarioViaGoogleSemSenha() {
        User user = User.registerWithGoogle("Bernardo", "Pinheiro", "bernardo@gmail.com");

        assertThat(user.getAuthProvider()).isEqualTo(AuthProvider.GOOGLE);
        assertThat(user.getPasswordHash()).isNull();
    }

    @Test
    void deveCompletarOnboarding() {
        User user = User.registerLocal("Bernardo", "Pinheiro", "bernardo@example.com", "+55", "hash");
        user.completeOnboarding(PrimaryGoal.INVESTMENT_GROWTH);

        assertThat(user.getOnboarding().isCompleted()).isTrue();
        assertThat(user.getOnboarding().getPrimaryGoal()).isEqualTo(PrimaryGoal.INVESTMENT_GROWTH);
    }

    @Test
    void deveTrocarSenhaDeUsuarioLocal() {
        User user = User.registerLocal("Bernardo", "Pinheiro", "bernardo@example.com", "+55", "hashAntigo");
        user.changePassword("hashNovo");
        assertThat(user.getPasswordHash()).isEqualTo("hashNovo");
    }

    @Test
    void deveRecusarTrocaDeSenhaParaUsuarioGoogle() {
        User user = User.registerWithGoogle("Bernardo", "Pinheiro", "bernardo@gmail.com");
        assertThatThrownBy(() -> user.changePassword("hashQualquer"))
                .isInstanceOf(BusinessRuleException.class);
    }

    @Test
    void deveAtualizarPerfil() {
        User user = User.registerLocal("Bernardo", "Pinheiro", "bernardo@example.com", "+55", "hash");
        user.updateProfile("Bernardo", "P. Silva", "+5585988887777", "https://avatar.png");

        assertThat(user.getLastName()).isEqualTo("P. Silva");
        assertThat(user.getAvatarUrl()).isEqualTo("https://avatar.png");
    }
}