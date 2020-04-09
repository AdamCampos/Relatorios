var j = 10;
var tipoOverride;

window.console = window.console || (function () {
    var c = {};
    c.log = c.warn = c.debug = c.imfo = c.error = c.time = c.dir
            = c.profile = c.clear = c.exception = c.trace = c.assert
            = function (s) {};
    return c;
})();

function reload() {
//O tempo de reload é em milésimos de segundo. Setado para 2 minutos
    // filtroAtivo();
    setTimeout("location.reload(true);", 120000);
}
;

function filtroInativo() {

    var entrada = 'Inativo';
    var filtro = entrada.toLowerCase().trim();
    var tr = document.getElementsByTagName("tr");
    var texto;
    var index;
    var ordemColuna;

    ordemColuna = 4;

    console.log("Quantidade de linhas (tableRows): " + tr.length);

    var linhaNova = 0;

    for (i = 0; i < tr.length; i++) {
        //O valor tr[i] é a linha de posição i e td[n] é a coluna na posição n
        var td = tr[i].getElementsByTagName("td")[ordemColuna];

        //Testa se td é um objeto não nulo (undefined)
        if (td) {
            //Operação binária or (bit a bit)
            texto = td.textContent.toLowerCase() || td.innerText.toLowerCase();
            index = texto.indexOf(filtro);
            //console.log("Index: " + index)
            if (index < 0) {
                tr[i].style.display = "none";
            } else {
                linhaNova++;
                //console.log("Linhas totais: " + linhaNova);
                if (linhaNova % 2 === 0) {
                    tr[i].style.backgroundColor = '#5882FA';
                } else if (linhaNova % 2 !== 0) {
                    tr[i].style.backgroundColor = '#D8D8D8';
                }
                //tr[i].style.display = "";
            }
        } else {
            console.log("Erro no td");
        }



    }
    ;
}
;

function pararRefresh() {
    console.log("Parando o refresh automático");
}
;

function filtroAtivo() {

    document.getElementById("divForm").style.display = "block";

    var entrada = 'Ativo';
    var filtro = entrada.toLowerCase();
    var tr = document.getElementsByTagName("tr");
    var texto;
    var index = 0;
    var linhaNova = 0;
    var ordemColuna;

    if (tipoOverride === 'OM') {
        ordemColuna = 7;
        document.getElementById('tituloHeader').innerHTML = "Relatório de Override de Manutenção.";
    } else if (tipoOverride === 'OO') {
        ordemColuna = 7;
        document.getElementById('tituloHeader').innerHTML = "Relatório de Override de Operação.";
    }

    for (i = 0; i < tr.length; i++) {
        //O valor tr[i] é a linha de posição i e td[n] é a coluna na posição n
        var td = tr[i].getElementsByTagName("td")[ordemColuna];
        //Testa se td é um objeto não nulo (undefined)
        if (td) {
            //Operação binária or (bit a bit)
            texto = td.textContent.toLowerCase() || td.innerText.toLowerCase();
            index = texto.indexOf(filtro);
//            console.log("Index: " + index);
//                console.log("aaaabbbb " + texto);
//                console.log("aaaacccc " + index);
            if (index < 0) {
                tr[i].style.display = "none";
            } else {
                linhaNova++;
                //console.log("Linhas totais: " + linhaNova);
                if (linhaNova % 2 === 0) {
                    tr[i].style.backgroundColor = '#7FB3D5';
                } else if (linhaNova % 2 !== 0) {
                    tr[i].style.backgroundColor = '#CCD1D1';
                }
            }
        } else {
            console.log("------------------------------------");
        }
    }

    if (linhaNova > 1) {
        document.getElementById('contador').innerHTML = "Total de " + linhaNova + " inibições.";
    } else if (linhaNova === 1) {
        document.getElementById('contador').innerHTML = "Total de " + linhaNova + " inibição.";
    } else {
        document.getElementById('contador').innerHTML = "Não foi encontrada nenhuma inibição.";
    }
}
;

function mostraHoraAtual() {
    var tempoAtual = new Date();
    var hora = tempoAtual.getHours();
    var min = tempoAtual.getMinutes();
    var sec = tempoAtual.getSeconds();
    var horario;
    horario = hora + ":" + min + ":" + sec;
//
//    console.log("Iniciando filtragem...");
//    console.log("Chamdas: " + j);
//    console.log("Hora: " + horario);
}
;

function listenerCheckTipo() {
    var chBoxes = document.getElementsByTagName("input");
    var todosElementos = document.getElementsByTagName("*");

    for (var k = 0; k < chBoxes.length; k++) {
        if (chBoxes[k].type === 'checkbox') {
            if (chBoxes[k].checked === true) {
                console.log('Tipo: ' + chBoxes[k].type + " " + chBoxes[k].value);
            }
        }
    }
//    for (var u = 0; u < todosElementos.length; u++) {
//        var elemento = todosElementos[u];
//        if (todosElementos[u].nodeName.includes("TH") &&  todosElementos[u].id.includes("col")) {
//            console.log(elemento.id);
//            if ( todosElementos[u].id.includes("colMotivoInibicao")) {
//                console.log( todosElementos[u]);
//            }
//        }
//
//    }
//
//    var tabela = document.getElementById("formTabela:tabelaOO");
//    var linhas = document.getElementsByTagName("tr");
//    for (var l = 0; l < linhas.length; l++) {
//        console.log("Linha: " + linhas[0]);
//    }

    for (var u = 0; u < todosElementos.length; u++) {
        var elemento = todosElementos[u];
        var nodeNome = elemento.nodeName;
        if (nodeNome.includes("th")) {
            console.log(nodeNome);
        }
    }

    console.log('------------------------------------------------');
}

function atualizaMensagemOverride(objeto) {

    tipoOverride = objeto.value;

    if (objeto.value === 'OM') {
        console.log("OM");
        document.getElementById('tituloHeader').innerHTML = "Relatório de Override de Manutenção";
    } else if (objeto.value === 'OO') {
        console.log('OO');
        document.getElementById('tituloHeader').innerHTML = "Relatório de Override de Operação";
    }
    
//    filtroAtivo();
    
}