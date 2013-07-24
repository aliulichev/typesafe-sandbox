$(function(){
// Makes upload button fancier
    $('#file').bootstrapFileInput();
    $('.alert').alert();
    $('.alert').hide();
    var updateProgress = function(progress) {
        $('#bar').width(Math.round(($('.container').width()/100) * progress));
        $('#bar').text(progress +'%');
    }

    var updateGrid = function(data){
        $('.alert').hide();
        $('#status').show();
        $("#personsTable tbody").empty();
        $(data).each(function(){
            $("#personsTable tbody").append(personToRaw(this))
        });
    }

    var personToRaw = function(person){
        return '<tr><td>' + person.name + '</td><td>'+ person.address +
        '</td><td>'+ person.postcode +'</td><td>'+ person.phone +
         '</td><td>'+ person.creditLimit +'</td><td>'+
          new Date(person.birthday).toFormattedString('dddd, MMMM ,yyyy') + '</td></tr>'
    }

    $('form').ajaxForm({
        beforeSend: function() {
            $('#status').show();
                updateProgress(0);
        },
        uploadProgress: function(event, position, total, percentComplete) {
            updateProgress(percentComplete);
        },
        success: function(xhr) {
            updateProgress(100)
        },
        error: function(xhr){
            updateProgress(0)
            $('#status').hide();
            $('.alert').show();
            $('#error').text(xhr.responseText);
                console.log('error');
        },
        complete: function(xhr) {
             if(xhr.status === 200){
                    updateGrid(JSON.parse(xhr.responseText));
             }
        }
    });

    $('#file').change(function(){
         $('form').submit();
     })
})