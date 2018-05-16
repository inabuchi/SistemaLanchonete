/**
 * 
 */

$( function() {
 
    // Cria objetos das divs de Pedido e Motoqueiro
    var $pedido = $( ".pedido" ),
      $motoqueiro = $( ".motoqueiro" );
 
    // Configurações para os itens dentro de Pedido serem arrastáveis
    $( "li", $pedido ).draggable({
      cancel: "a.ui-icon", // Faz com que clicar na lupa não permita arrastar
      revert: "invalid", // Se colocado em um lugar fora da área aceita, o objeto retorna à posição inicial
      containment: "document",
      helper: "clone",
      cursor: "move"
    });
 
    // Permite que os pedidos possam ser arrastados de Motoqueiro, aceitando tanto os que vêm do Pedido quanto de outros motoqueiros
    $motoqueiro.droppable({
      accept: "#pedido > li, .motoqueiro li",
      classes: {
        "ui-droppable-active": "motoqueiro-highlight ui-state-highlight"
      },
      drop: function( event, ui ) {
		$motoqueiro =  $( this );
        adicionaMotoqueiro( ui.draggable );
      }
    });
 
    // Permite que os pedidos possam ser arrastados de Pedido para qualquer Motoqueiro
    $pedido.droppable({
      accept: ".motoqueiro li",
      classes: {
        "ui-droppable-active": "custom-state-active pedido-highlight"
      },
      drop: function( event, ui ) {
        adicionaAListaPedido( ui.draggable );
      }
    });
 
    // Função para adicionar pedido ao motoqueiro
    var recycle_icon = "<a href='link/to/recycle/script/when/we/have/js/off' title='Retornar para a lista de pedido' class='ui-icon ui-icon-refresh'>Retorna Pedido</a>";
    function adicionaMotoqueiro( $item ) {
      $item.fadeOut(function() {
        var $list = $( "ul", $motoqueiro ).length ?
          $( "ul", $motoqueiro ) :
          $( "<ul class='pedido ui-helper-reset'/>" ).appendTo( $motoqueiro );
 
        $item.find( "a.ui-icon-refresh" ).remove(); //Remove o ícone de retornar à lista de pedidos. Qunado o pedido é arrastado de um motoqueiro para outro, é criada uma cópia do ícone.
        $item.append( recycle_icon ).appendTo( $list ).fadeIn(function() {
          $item
            .animate({ width: "96px" })
            .find( "img" )
        });
      });
    }
 
    // Função para retornar algum pedido que esteja em um Motoqueiro para a lista de Pedidos    
    function adicionaAListaPedido( $item ) {
      $item.fadeOut(function() {
        $item
          .find( "a.ui-icon-refresh" )
            .remove()
          .end()
          .appendTo( $pedido )
          .fadeIn();
      });
    }
 
	//Define as configurações de como a janela de diálogo irá ser apresentada
	$( "#dialog-pedido" ).dialog({
      autoOpen: false,
      height: 400,
      width: 350,
      modal: true,
	  close: function() {
        $( "#dialog-conteudo" ).empty(); 	//.empty() remove todo o conteúdo do objeto
      }
	});

	
    // Abre uma janela de diálogo com as informações do pedido que estão dentro da tag <p> "descOculta"
    function verInfoPedido( $item ) {
		$item
          .find( ".descOculta" ).clone().removeClass("descOculta") //.find() procura uma <TAG>.CLASSE para colocar no dialog, clone() cria outra e deixa a original intácta
          .appendTo( $( "#dialog-conteudo" ) ); 	//insere a tag <p> dentro do dialog (modal)
     $( "#dialog-pedido" ).dialog( "open" ); 	//Abre a modal
    }
 
    // Dá à cada ícone sua devida função
    $( "ul.pedido > li" ).on( "click", function( event ) {
      var $item = $( this ),
        $target = $( event.target );
 
      if ( $target.is( "a.ui-icon-zoomin" ) ) {
        verInfoPedido( $item );
      } else if ( $target.is( "a.ui-icon-refresh" ) ) {
        adicionaAListaPedido( $item );
      }
 
      return false;
    });
  } );