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
    debugger;
    const params = {
      nmUsuario: user.val(),
      dsSenha: senha.val()
    }
    const urlRequest = "/usuarios";
    if (validaLogin()) {
      $.ajax({
        type: 'GET'
        , dataType: 'json'
        , url: urlRequest
        , data: params
      }).done(response => {
        if (true) {
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


