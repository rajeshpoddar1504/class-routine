<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <!-- Include the Tailwind CSS file here -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
    <link rel="stylesheet" href="/css/custom.css">
    <title>College Timetable</title>
</head>
<body>
<body>
 
<div class="container mt-5 px-1 canvas_div_pdf" id="htmlContent" >

<div class="download-link"><a href="${download_url}" id="download-routine" class="link-primary" style="text-align:right;">Download+</a></div>
    <h1 class="text-center "><u> Class Routine 2024</u></h1>
    <h3 class="text-center"> Department of Computer Science and Engineering</h3>
    <h3 class="text-center mb-3">Bangamata Sheikh Fojilatunnesa Mujib Science & Technology University</h3>
	<div class="table-responsive">
    <table class="table table-bordered text-center">
    <c:forEach items="${routine_data }" var="routine_data_row" end="0">
    
    <thead>
        <tr>
        <c:set var="head_count" value="0"/>
    <c:forEach var="routine_data_row_map" items="${routine_data_row }" >
    	<c:if test="${routine_data_row_map.value ne '' or not empty routine_data_row_map.value }">
    	<c:set var="head_count1" value="${head_count1+1 }" scope="page"></c:set>
        <th scope="col">${routine_data_row_map.value }</th></c:if>
    </c:forEach>
    </tr>
        </thead>
    
    </c:forEach>
        <tbody>
        <!-- Time slots -->
        <c:forEach items="${routine_data }" var="routine_data_row" begin="1" end="${routine_data.size() }">
        <tr>
        <c:forEach items="${routine_data_row }" var="routine_data_row_map" begin="0" end="${head_count1-1 }">
            <td >${routine_data_row_map.value }</td>
        </c:forEach>
         </tr>
        </c:forEach>
       
        </tbody>
    </table>
    </div>
    
</div>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js "></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>

<!-- <script src="https://html2canvas.hertzen.com/dist/html2canvas.js"></script>
 --><script type="text/javascript" src="/js/custom.js" ></script>

</html>