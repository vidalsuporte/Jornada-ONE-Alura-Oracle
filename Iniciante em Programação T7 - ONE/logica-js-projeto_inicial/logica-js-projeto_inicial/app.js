alert('Bem vindo ao jogo no número secreto!');
let numeroSecreto = Math.random * 10;
let chute = prompt('Escolha um número ente 1 e 10. ');
let tentativas = 1;


console.log(numeroSecreto);
if(chute == numeroSecreto){

    alert(`Você acertou o numero secreto com ${tentativas} ${tentativas > 1? 'tentativas':'tentativa'}!`);    
}else{
if(chute > numeroSecreto){

    alert('o chute é maior que o numero secreto');
}else{

    alert('o chute é menor que o numero secreto');
}
tentativas ++;

}
