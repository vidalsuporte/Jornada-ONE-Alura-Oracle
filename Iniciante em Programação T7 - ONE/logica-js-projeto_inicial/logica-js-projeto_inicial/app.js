alert('Bem vindo ao jogo no número secreto!');
let numeroLimite = 100;
let numeroSecreto = parseInt(Math.random() * numeroLimite + 1);
console.log(numeroSecreto);
let chute ;
let tentativas = 1;


while(chute != numeroSecreto){

chute = prompt(`Esolha um numero de 1 a ${numeroLimite}.`)
    if(chute == numeroSecreto){
        break;
    }else{
        if(chute > numeroSecreto){

            alert('o chute é maior que o numero secreto.');
        }else{

            alert('o chute é menor que o numero secreto.');
        }
tentativas ++;

}

}

alert(`Você acertou o numero secreto com ${tentativas} ${tentativas > 1? 'tentativas':'tentativa'}!`); 