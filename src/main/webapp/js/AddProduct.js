$("button.create")
    .click(
        function (event) {
            event.preventDefault();
            var name = $("form.addProduct input.name").val();
            var description = $("form.addProduct input.description").val();
            var price = $("form.addProduct input.price").val();

            var product = {
                name: name,
                description: description,
                price: price
            };
//add validation
            $.post("api/products", product)
                .done(function (data, textStatus, xhr) {
                    alert('Success');
                    $("form")[0].reset();
                })
                .fail(function (data, textStatus, xhr) {
                    alert(data.responseText);
                });
        });