<#macro header>
    <p align="right">Ректору КФУ<br>
        профессору Гафурову И.Р<br>
        от ${user.surname} ${user.name}<br>
        ${user.phone}</p>
    <p align="center"><b>Заявление</b></p><br>
    <p align="center">Прошу Вас предоставить мне дополнительный отпуск для прохождения промежуточной аттестации</p><br>
    <div class="ch">Подпись<div><br>
    <div class="ch">Дата</div>
</#macro>
<#macro form>
    <form method="post" action="/send">
    <input type="text" name="name" placeholder="name"><br>
    <input type="text" name="passportId" placeholder="passport id"><br>
    <input type="text" name="phone" placeholder="phone"><br>
    <input type="text" name="surname" placeholder="surname"><br>
    <input type="number" name="age" placeholder="age"><br>
    <input type="text" name="dateOfIssue" placeholder="Date of issue"><br>
    <button type="submit"></button>
</form>
</#macro>