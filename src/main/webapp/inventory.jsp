<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management</title>
    <style><%@include file="../css/inventory.css"%></style>
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="home">Home</a></li>
                <li><a href="inventory">Inventory</a></li>
                <li><a href="sales">Sales</a></li>
                <li><a href="accounting">Accounting</a></li>
                <li><a href="login">Login</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section class="inventory">
            <h1>Inventory</h1>

            <div class="actions">
                <form action="inventory" method="POST">
                	<input type="hidden" name="formType" value="creationForm">
                    <input type="text" name="name" placeholder="Product Name" required>
                    <input type="text" name="description" placeholder="Description" required>
                    <input type="number" name="price" placeholder="Price" step=".01"required>
                    <input type="number" name="stock" placeholder="Stock" required>
                    <button type="submit">Add Product</button>
                </form>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Product> products = (List<Product>) request.getAttribute("products");
                        if (products != null) {
                            for (Product product : products) { 
                    %>
                        <tr>
                            <td><%= product.getId() %></td>
                            <td><%= product.getName() %></td>
                            <td><%= product.getDescription() %></td>
                            <td><%= product.getPrice() %></td>
                            <td><%= product.getQuantity() %></td>
                            <td>
                                
                                <form action="inventory" method="POST" style="display:inline;">
                                	<input type="hidden" name="formType" value="buttonForm">
                                	<button type="button" class="edit-btn" 
                                		name="edit"
                                        data-id="<%= product.getId() %>" 
                                        data-name="<%= product.getName() %>"
                                        data-description="<%= product.getDescription() %>" 
                                        data-price="<%= product.getPrice() %>" 
                                        data-stock="<%= product.getQuantity() %>">
	                                    Edit
	                                </button>
                                    <button type="submit" name="delete" value="<%= product.getId() %>">Delete</button>
                                </form>
                            </td>
                        </tr>
                    <% 
                            }
                        } 
                    %>
                </tbody>
            </table>
        </section>
    </main>

    <!-- Modal Structure -->
    <div id="editModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Edit Product</h2>
            <form id="editForm" action="inventory" method="POST">
            	<input type="hidden" name="formType" value="editForm">
                <input type="hidden" id="editProductId" name="id">
                <label for="editProductName">Name:</label>
                <input type="text" id="editProductName" name="name" required><br><br>
                <label for="editProductDescription">Description:</label>
                <input type="text" id="editProductDescription" name="description" required><br><br>
                <label for="editProductPrice">Price:</label>
                <input type="number" id="editProductPrice" name="price" step=".01" required><br><br>
                <label for="editProductStock">Stock:</label>
                <input type="number" id="editProductStock" name="stock" required><br><br>
                <button type="submit">Save Changes</button>
            </form>
        </div>
    </div>

    <!-- JavaScript for Modal Functionality -->
    <script>
        // Get modal element and close button
        var modal = document.getElementById("editModal");
        var span = document.getElementsByClassName("close")[0];

        // Get all edit buttons
        var editButtons = document.getElementsByClassName("edit-btn");

        // Loop through all edit buttons
        Array.prototype.forEach.call(editButtons, function(btn) {
            btn.addEventListener("click", function() {
                // Get product data from the button's data attributes
                var productId = this.getAttribute("data-id");
                var productName = this.getAttribute("data-name");
                var productDescription = this.getAttribute("data-description");
                var productPrice = this.getAttribute("data-price");
                var productStock = this.getAttribute("data-stock");

                // Set modal input values
                document.getElementById("editProductId").value = productId;
                document.getElementById("editProductName").value = productName;
                document.getElementById("editProductDescription").value = productDescription;
                document.getElementById("editProductPrice").value = productPrice;
                document.getElementById("editProductStock").value = productStock;

                // Show modal
                modal.style.display = "block";
            });
        });

        // Close the modal when the user clicks the close button
        span.onclick = function() {
            modal.style.display = "none";
        };

        // Close the modal if the user clicks outside of the modal
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        };
    </script>
</body>
</html>
