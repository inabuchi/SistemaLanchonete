const form = $("#formCliente");
form.ready(() => {
    const btnSave = $("#btnSaveCliente");

    btnSave.click(() => {
        const name = $('#nomeCli');
        const phone1 = $('#telCli01');
        const phone2 = $('#telCli02');
        const obsCli = $('#obsCli');
        const rua = $('#endCli');
        const nr = $('#numEndCli');
        const logradouro = $('#logrEndCli');
        const cep = $('#cepCli');
        const bairro = $('#baiCli');
        const complemento = $('#compEndCli');
        const cidade = $('#codCidCli');
        const uf = $('#ufCli');
        const obsEnd = $('#obsEndCli')

        const validaFomularioCliente = () => {
            let mandatoryFields = '';
            if (!name.val()) {
                mandatoryFields += "Nome, ";
            } 
            if (!phone1.val()) {
                
                let res = validarTelefone(phone1.val());
                if (!res.message && !res.valido) {
                    mandatoryFields += 'Telefone, ';
                } else if (res.message) {
                    alert(res.message);
                    return false;
                }
                res = validaEndereco(rua, nr, cep, bairro, cidade);
                if (res.message) {
                    alert(res.message);
                    return false;
                } else if (!res.valido && res.mandatoryFields) {
                    mandatoryFields += res.mandatoryFields
                }
            }
    
            if (mandatoryFields) {
                length = mandatoryFields.length;
                alert('Favor informe o(s) seguite(s) campo(s) ' + mandatoryFields.substr(0, (length-2)) + '!');
                return false;
            }
            return true;
        };

        const urlRequest = "/clientes/save";
        if (validaFomularioCliente()) {
          $.ajax({
            type: 'POST'
            , dataType: 'json'
            , url: '/clientes'
            , data: params
          }).done(response => {
            alert("Cliente cadastrado com sucesso!");
          }).fail(response => {
            alert("Falha no login, tente novamente!");
          });
        }
      });
});