$('input[type=radio][name=upload-category]').change(function() {
    if (this.value == 'student') {
	$('#select-faculty').prop('disabled', 'disabled');
    }
    else if (this.value == 'faculty') {
        $('#select-faculty').prop('disabled', false);
    }
});


function getPDF(){
/*
		var HTML_Width = $(".canvas_div_pdf").width();
		var HTML_Height = $(".canvas_div_pdf").height();
		var top_left_margin = 15;
		var PDF_Width = HTML_Width+(top_left_margin*2);
		var PDF_Height = (PDF_Width*1.5)+(top_left_margin*2);
		var canvas_image_width = HTML_Width;
		var canvas_image_height = HTML_Height;
		
		var totalPDFPages = Math.ceil(HTML_Height/PDF_Height)-1;
		

		html2canvas($(".canvas_div_pdf")[0],{allowTaint:true}).then(function(canvas) {
			canvas.getContext('2d');
			
			console.log(canvas.height+"  "+canvas.width);
			
			
			var imgData = canvas.toDataURL("image/jpeg", 1.0);
			var pdf = new jsPDF('p', 'pt',  [PDF_Width, PDF_Height]);
		    pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin,canvas_image_width,canvas_image_height);
			
			
			for (var i = 1; i <= totalPDFPages; i++) { 
				pdf.addPage(PDF_Width, PDF_Height);
				pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4),canvas_image_width,canvas_image_height);
			}
			
		    pdf.save("HTML-Document.pdf");
        });*/

	};

window.jsPDF = window.jspdf.jsPDF;
var docPDF = new jsPDF();

function downloadPDF(invoiceNo){

    var elementHTML = document.querySelector("#htmlContent");
    docPDF.html(elementHTML, {
        callback: function(docPDF) {
            docPDF.save('time_table'+'.pdf');
        },
        x: 1,
        y: 1,
        width: 200,
        windowWidth: 1200
    });
}	

/*document.getElementById('download-routine').addEventListener('click', function(ev){
    ev.preventDefault();
    // DO SOMETHING WITH THE HREF like use it for the ajax call
    var ajaxCall = new XMLHttpRequest();
    ajaxCall.open('GET', this.href);
    ajaxCall.send();    
});*/

/*$(document)
.on('click', 'form input[type=submit]', function(e) {
    var isValid = $(e.target).parents('form').isValid();
    if(!isValid) {
      e.preventDefault(); //prevent the default action
    }
});*/