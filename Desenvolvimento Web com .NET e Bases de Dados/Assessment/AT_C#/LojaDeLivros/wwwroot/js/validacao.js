function validarValor(input) {
    var valorFormatado = input.value.replace(/\s/g, '').replace('.', ',');

    if (!/^\d+(,\d{0,2})?$/.test(valorFormatado)) {
        alert("Por favor, insira um valor numérico válido com até duas casas decimais, utilizando vírgula como separador decimal.");
        input.value = '';
    }
}

function validarNumero(input) {
    var valorFormatado = input.value.replace(/\s/g, '');

    if (!/^\d{0,4}$/.test(valorFormatado)) {
        alert("Por favor, insira até 4 dígitos numéricos.");
        input.value = '';
    }
}