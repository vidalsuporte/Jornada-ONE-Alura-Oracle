let numeroLimite = 100;
let listaDenumerosSorteados = [];
let numeroSecreto = criaNumeroSecreto();
console.log(numeroSecreto);
let tentativas = 1;

exibirMessagemInicial();

function exibirTextoNaTela(tag, texto){
let campo = document.querySelector(tag);
campo.innerHTML = texto;
responsiveVoice.speak(texto, 'Brazilian Portuguese Female', {rate:1.2});

}

function exibirMessagemInicial(){

    exibirTextoNaTela('h1','Jogo do número secreto');
    exibirTextoNaTela('p',`Escolha um número de 1 à ${numeroLimite}.`);


}

function verificarChute (){
    console.log(listaDenumerosSorteados);
    let chute = document.querySelector('input').value;
    if(chute == numeroSecreto){
        exibirTextoNaTela('h1','Acertou!');
        let menssagem = `Você descobriu o número secreto com ${tentativas} ${tentativas > 1? 'tentativas': 'tentativa'}!`
        exibirTextoNaTela('p',menssagem);
        document.getElementById('reiniciar').removeAttribute('disabled');
        document.getElementById('chutar').setAttribute('disabled', true);
    }else{
        if(chute > numeroSecreto){
            exibirTextoNaTela('p','O chute é maior que o número secreto!');
        }else{
            exibirTextoNaTela('p','O chute é menor que o número secreto!');
        }
        
        tentativas ++;
        limparCampo();
    }
   
    }
 


function criaNumeroSecreto(){
let numeroEscolhido =  parseInt(Math.random() * numeroLimite + 1);
let quantidadeElementosNaLista = listaDenumerosSorteados.length;
if(quantidadeElementosNaLista == numeroLimite){
    listaDenumerosSorteados = [];
}
if(listaDenumerosSorteados.includes(numeroEscolhido)){
    return criaNumeroSecreto();
}else{
    listaDenumerosSorteados.push(numeroEscolhido);
    return numeroEscolhido;
    
}

console.log(numeroEscolhido);


}

function limparCampo(){
    chute = document.querySelector('input');
    chute.value = '';
}

function reiniciarJogo(){
numeroSecreto = criaNumeroSecreto();
exibirMessagemInicial();
tentativas = 1;
limparCampo();
document.getElementById('reiniciar').setAttribute('disabled', true);
document.getElementById('chutar').removeAttribute('disabled');

}