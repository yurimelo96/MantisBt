#language: pt
Funcionalidade: Login no site MantisBT

  @C001_CT01
  Cenario: C001_CT01-LoginSistemaFalha
    Dado que preencho o login e senha no sistema MantisBT com as credenciais "yuri.melo" e "Rayquaza101"
    Quando clico no botao Login
    Entao valido a mensagem de erro

  @C001_CT02
  Cenario: C001_CT02-LoginReportIssue
    Dado que preencho o login e senha no sistema MantisBT com as credenciais "yuri.melo" e "Rayquaza101."
    Quando clico no botao Login
    Entao valido a mensagem de sucesso
    Quando clico na aba Report Issue
		E seleciono o projeto
		Entao preencho o formulario com o upload