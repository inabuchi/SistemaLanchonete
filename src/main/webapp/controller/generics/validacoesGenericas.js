var  validarTelefone = (telefone) => {
    const retorno = {
        message: '',
        valido: true
    }
    if (!telefone) {
        retorno.valido = false;
    } else if (telefone.length < 8 || telefone.length > 11) {
        retorno.valido = false;
        retorno.message = 'O número de telefone informado é inválido. Verifique!'
    } 
    return retorno;
}

var validaEndereco = (rua, nr, cep, bairro, cidade) => {
    var retorno = {
        valido: true,
        message: '',
        mandatoryFields: ''
    };
    if (!rua) {
        retorno.valido = false; 
        retorno.mandatoryFields += 'Rua, ';
    }
    if (!nr) {
        retorno.valido = false; 
        retorno.mandatoryFields += 'Número, ';
    }
    if (!cep) {
        retorno.valido = false; 
        retorno.mandatoryFields += 'Cep, ';
    } else {
        if (cep.length > 8 || cep.length < 8) {
            retorno.valido = false; 
            retorno.message = 'Cep informado é inválido.Verifique!';
        }
    }
    if (!bairro) {
        retorno.valido = false; 
        retorno.mandatoryFields += 'Bairro, ';
    }
    if (!cidade) {
        retorno.valido = false; 
        retorno.mandatoryFields += 'Cidade, ';
    }
    return retorno;
};


    
