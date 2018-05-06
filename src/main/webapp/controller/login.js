/**
 *
 * @author Everton
 */

const form = $("#login");

form.ready(() => {
  const user = $("#usuario");
  const senha = $("#senha");
  const btnLogin = $("#btnLogin");

  const validaLogin = () => {
    let hasUser = true;
    let hasPass = true;
    if (!user.val()) {
      hasUser = false;
    }

    if (!senha.val()) {
      hasPass = false;
    }

    if (!hasPass && !hasUser) {
      alert(unescape("Usuário e senha não informados!"));
      return false;
    } else if (!hasUser) {
      alert(unescape("Usuário não informado!"));
      return false;
    } else if (!hasPass) {
      alert("Senha não informada!");
      return false;
    }
    return true;
  }

  btnLogin.click(() => {
    const params = {
      nmUsuario: user.val(),
      dsSenha: senha.val()
    }
    const urlRequest = "action/login";
    if (validaLogin()) {
      $.ajax({
        type: 'post'
        , dataType: 'json'
        , url: 'http://localhost:8080/SistemaLanchonete/services/login/login'
        , data: params
      }).done(response => {
        if (response.success) {
          alert("Login efetuado com sucesso!");
          window.location = "GerenciarClienteView.jsp";
        } else if (response.erro) {
          alert(response.erro);
        }
      }).fail(response => {
        alert("Falha no login, tente novamente!");
      });
    }
  });
});


