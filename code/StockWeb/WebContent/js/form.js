function initComp() {
	jQuery('#time').datetimepicker({
        timeFormat: "HH:mm:ss",
        dateFormat: "yy-mm-dd"
    });
    
    $('#price').change(function(){
        caculateFee();
    });
    
    $('#quantity').on('input', function(){
        caculateFee();  
    });
}

function caculateFee() {
	var quantity = $('#quantity').val();
	var price = $('#price').val();
	$.ajax({     
       	type: 'Post',     
       	url:  './query?serviceCode=queryTransactionFee&transactionAction=1&transactionQuantity=' + quantity + '&stockPrice=' + price,     
       	contentType: 'application/json; charset=utf-8',     
       	dataType: 'json',     
        success: loadFee,     
        error: function(err) {     
            alert(err);     
        }     
	});  
}

function loadFee(data) {
	$('#totalPrice').text(data.totalPrice);
	$('#fee').text(data.fee);
}

function initForm(data) {
	$('#code').val(data.code);
	$('#price').val(data.currentPrice);
	$('#quantityTitle').text('Quantity(max:' + data.quantity + ')');
	$('#transId').val(data.transId);
}