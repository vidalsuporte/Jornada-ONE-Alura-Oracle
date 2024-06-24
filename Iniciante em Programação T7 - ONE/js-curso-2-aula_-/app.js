
let numeroLimite = 100;
let numeroSecreto = criaNumeroSecreto(numeroLimite);
console.log(numeroSecreto);
let tentativas = 1;

exibirMessagemInicial();

function exibirTextoNaTela(tag, texto){
let campo = document.querySelector(tag);
campo.innerHTML = texto;
}

function exibirMessagemInicial(){

    exibirTextoNaTela('h1','Jogo do número secreto');
    exibirTextoNaTela('p',`Escolha um número de 1 à ${numeroLimite}.`);


}

function verificarChute (){
    let chute = document.querySelector('input').value;
    if(chute == numeroSecreto){
        exibirTextoNaTela('h1','Acertou!');
        let menssagem = `Você descobriu o número secreto com ${tentativas} ${tentativas > 1? 'tentativas': 'tentativa'}!`
        exibirTextoNaTela('p',menssagem);
        document.getElementById('reiniciar').removeAttribute('disabled');

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



function criaNumeroSecreto(numeroMaximo){
return parseInt(Math.random() * numeroMaximo + 1);
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

}