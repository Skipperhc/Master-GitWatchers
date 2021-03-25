function isEmpty(str) {
	return (!str || 0 === str.length);
}

function SweetAlertErro() {
	var erro = document.querySelectorAll('body')[0].getAttribute('erroFuncao');

	if (isEmpty(erro) == false) {
		swal({
			title: 'Atenção',
			text: erro,
			icon: "error",
			dangerMode: false,
		});
	}
}

function SweetAlertSucesso(titulo, textoConteudo) {
	swal({
		title: titulo,
		text: textoConteudo,
		icon: "success",
		dangerMode: false,
	});
}
