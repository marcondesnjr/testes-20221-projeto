# Projeto da disciplina de Testes

* Os testes unitários foram implementados utilizando JUnit e são executados
pelo plugin **surefire** e podem ser durante a *phase* **test** 


* Os testes de integração foram implementados utilizando DBUnit e são 
executados pelo plugin **failsafe** na *phase* **integration-test**



* Os [testes de sistema](https://github.com/marcondesnjr/testes-20221-projeto/tree/master/testes-sistema) 
foram implementados utilizando sellenium com java script e podem ser
executados utilizando **node testes-sistema/testSelenium.js**


### Ambos os testes podem ser executados utilizando o comando:

**$ mvn verify**

### Projeto pode ser executado utilizando serviços docker-compose com o script
**$ ./docker-compose.sh**