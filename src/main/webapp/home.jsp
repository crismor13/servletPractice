<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Product, models.Sale" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Beauty Shop Dashboard</title>
    <style><%@include file="../css/home.css"%></style>
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
        <section class="dashboard">
            <h1>Dashboard Overview</h1>

            <!-- Products Overview -->
            <div class="dashboard-cards">
                <div class="card">
                    <h2>Low Stock Alerts</h2>
                    <ul>
                        <% 
                            List<Product> lowStockProducts = (List<Product>) request.getAttribute("lowStockProducts");
                            for (Product product : lowStockProducts) { 
                        %>
                            <li><%= product.getName() %> - <%= product.getQuantity() %> in stock</li>
                        <% } %>
                    </ul>
                </div>

                <!-- Sales Overview -->
                <div class="card">
                    <h2>Recent Sales</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th>Quantity</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                List<Sale> recentSales = (List<Sale>) request.getAttribute("recentSales");
                                for (Sale sale : recentSales) { 
                            %>
                                <tr>
                                    <td><%= sale.getId() %></td>
                                    <td><%= sale.getQuantity() %></td>
                                    <td><%= sale.getSaleDate() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>

                <!-- Notifications -->
                <div class="card">
                    <h2>Notifications</h2>
                    <ul>
                        <li>Restock Lipsticks soon!</li>
                        <li>Next order shipment due tomorrow</li>
                    </ul>
                </div>
            </div>
        </section>
    </main>
</body>
</html>
