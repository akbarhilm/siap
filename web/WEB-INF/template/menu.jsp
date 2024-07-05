<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<li class="dropdown-submenu">                                                
    <a tabindex="-1" href="javascript:;">
        <c:out value="${node.nama}" />
    </a>                                                    
    <ul class="dropdown-menu">
        <c:forEach items="${node.menu}" var="node" >
           <c:set var="node" value="${node}" scope="request" />
            <c:choose>
                <c:when test="${node.kodeAdaSubMenu == 'Y'}">
                    <jsp:include page="menu.jsp" />
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}<c:out value='${node.link}' />"><c:out value='${node.nama}' /> </a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>                                            
</li>
<c:remove var="node" scope="request"/>                                                                                                                                                                                                                                                                                            
