#language: ru
Функционал: Cucumber

  Предыстория:
    * пользователь наводит на пункт меню Ипотека
    * пользователь нажимает на подпункт меню Ипотека на готовое жильё
    * пользователь меняет значение поля Стоимость недвижимости на "5180000"
    * пользователь меняет значение поля Первоначальный взнос на "3058000"
    * пользователь меняет значение поля Срок кредита на "30\n"
#    * пользователь нажимает на переключатель Есть зарплатная карта Сбербанка
#    * пользователь нажимает на переключатель Молодая семья

  @all @success
  Сценарий: проверка поля Сумма кредита
    * выполнеяется сравнение поля Сумма кредита с параметром "2 122 000 ₽"

  @all @success
  Сценарий: проверка поля Ежемесячный платеж
#    * выполнеяется сравнение поля Ежемесячный платеж с параметром "18 623 ₽"
    * выполнеяется сравнение поля Ежемесячный платеж с параметром "17 843 ₽"

  @all @success
  Сценарий: проверка поля Необходимый доход
#    * выполнеяется сравнение поля Необходимый доход с параметром "31 037 ₽"
    * выполнеяется сравнение поля Необходимый доход с параметром "29 739 ₽"

  @all @fail
  Сценарий: проверка поля Процентная ставка
    * выполнеяется сравнение поля Процентная ставка с параметром "11,0 %"