const {Builder, Browser, By, Key, until} = require('selenium-webdriver');
const {resolve} = require('path')
const assert = require('assert')




async function testCadastrarUsuario() {
    let driver = await new Builder().forBrowser(Browser.CHROME).build();

    const documentInitialised = () =>
        driver.executeScript('return initialised');

    var id = new Date().valueOf();
    try {
        await driver.get('http://localhost:8080/SisMovieWeb/singin/page');
        var uploadImg = await driver.findElement(By.xpath('//*[@id="foto"]'));
        await uploadImg.sendKeys(resolve('./tst.png'))
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
        //await driver.findElement(By.name('q')).sendKeys('webdriver', Key.RETURN);
        //await driver.wait(until.titleIs('webdriver - Google Search'), 1000);
    } finally {
        //await driver.quit();
    }
}

testCadastrarUsuario()