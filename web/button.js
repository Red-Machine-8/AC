function btnClick()
{
    var name = document.getElementById('username').value;
    var gender = document.getElementById('gender').value;
    var year = document.getElementById('year').value;

    $.ajax({
        url: '/read',
        data: {"Name": name,  "Gender": gender, "Year": year},
        method: 'GET',
        success: function res (data){console.log(data)}
    });

}


