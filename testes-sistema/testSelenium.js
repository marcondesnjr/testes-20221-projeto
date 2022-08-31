const {Builder, Browser, By, Key, until} = require('selenium-webdriver');
const path = require('path')
const assert = require('assert')


var imagepath = path.join(__dirname, 'tst.png');

async function testCadastrarUsuario() {
    let driver = await new Builder().forBrowser(Browser.CHROME).build();

    const documentInitialised = () =>
        driver.executeScript('return initialised');

    var id = new Date().valueOf();
    try {
        await driver.get('http://localhost:8080/SisMovieWeb/singin/page');
        var uploadImg = await driver.findElement(By.xpath('//*[@id="foto"]'));
        await uploadImg.sendKeys(imagepath)
        await driver.findElement(By.xpath('//*[@id="nome"]'))
            .sendKeys(`nome-${id}`);
        await driver.findElement(By.xpath('//*[@id="sobrenome"]'))
            .sendKeys(`sobrenome-${id}`);
        await driver.findElement(By.xpath('//*[@id="apelido"]'))
            .sendKeys(`apelido-${id}`);
        await driver.findElement(By.xpath('//*[@id="email"]'))
            .sendKeys(`email-${id}@email`);
        await driver.findElement(By.xpath('//*[@id="senha"]'))
            .sendKeys(`senha-${id}`);
        await driver.findElement(By.xpath('//*[@id="dataNasc"]'))
            .sendKeys(`12121212`);
        await driver.findElement(By.xpath('//*[@id="cidade"]'))
            .sendKeys(`cidade-${id}`);
        await driver.findElement(By.xpath('//*[@id="estado"]'))
            .sendKeys(`Acre`);
        await driver.findElement(By.xpath('/html/body/div[2]/div/div[2]/form/div[10]/input'))
            .click()

        await driver.wait(until.elementLocated(By.id('login')), 10000);

        await driver.findElement(By.xpath('//*[@id="login"]'))
            .sendKeys(`email-${id}@email`)
        await driver.findElement(By.xpath('//*[@id="senha"]'))
            .sendKeys(`senha-${id}`)
        await  driver.findElement(By.xpath('/html/body/div[2]/div/div/form/input'))
            .click()
        var text = await  driver.wait(until.elementLocated(By.xpath('//*[@id="bs-example-navbar-collapse-1"]/ul/li[2]/a'))).getText()

        assert.strictEqual(text, "Minha Conta")
        console.info("Teste 'testCadastrarUsuario' Completado com Sucesso")
    } finally {
        await driver.quit();
    }
}

async function testCadastrarFilme() {
    let driver = await new Builder().forBrowser(Browser.CHROME).build();

    const documentInitialised = () =>
        driver.executeScript('return initialised');

    var id = new Date().valueOf();
    try {
        await driver.get('http://localhost:8080/SisMovieWeb/index/');
        await driver.wait(until.elementLocated(By.id('login')))
            .sendKeys('admin@admin.com')
        await driver.findElement(By.id('senha'))
            .sendKeys('admin')
        await driver.findElement(By.xpath('/html/body/div[2]/div/div/form/input'))
            .click()


        await driver.wait(until.elementLocated(By.id('dropdownMenu1')))
            .click()
        await driver.findElement(By.xpath('//*[@id="bs-example-navbar-collapse-1"]/ul/li[7]/div/ul/li[1]/a'))
            .click()


        var uploadImg = await driver.findElement(By.id('foto'));
        await uploadImg.sendKeys(imagepath)
        await driver.findElement(By.id('titulo'))
            .sendKeys(`titulo-${id}`);
        await driver.findElement(By.id('sinopse'))
            .sendKeys(`sinopse-${id}`);
        await driver.findElement(By.id('ano'))
            .sendKeys(`2000`);
        await driver.findElement(By.id('ator'))
            .sendKeys(`ator-${id}`);
        await driver.findElement(By.id('diretor'))
            .sendKeys(`diretor-${id}`);
        await driver.findElement(By.xpath('/html/body/div[2]/div/div/form/input'))
            .click()
        let tituloActual = await  driver.wait(until.elementLocated(By.xpath('/html/body/div[4]/div/div[2]/div[1]/h2/strong'))).getText()
        assert.strictEqual(tituloActual,`titulo-${id}`);
        console.info("Teste 'testCadastrarFilme' Completado com Sucesso")
    } finally {
        await driver.quit();
    }
}

testCadastrarUsuario()
    .then(() => testCadastrarFilme())
    .finally( () => console.log("TESTES REALIZADOS"))
