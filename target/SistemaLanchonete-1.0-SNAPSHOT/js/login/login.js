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
    if (!user.val()) {
      alert(unescape("Usuário não informado"));
      return false;
    } else if (!senha.val()) {
      alert("Senha não informada");
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
        , url: "LoginApi.java"
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


