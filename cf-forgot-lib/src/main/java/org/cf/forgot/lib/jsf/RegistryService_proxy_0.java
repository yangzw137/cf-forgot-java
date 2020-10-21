package org.cf.forgot.lib.jsf;//package com.jd.jsf.service;
//
//import com.jd.jsf.gd.client.ClientProxyInvoker;
//
///**
// * Description:
// * <p>
// * @date 2020/9/10
// */
//public class RegistryService_proxy_0 {
//
//    private ClientProxyInvoker proxyInvoker;
//
//    public com.jd.jsf.vo.SubscribeUrl lookup(com.jd.jsf.vo.JsfUrl arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "lookup";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = com.jd.jsf.vo.JsfUrl.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return (com.jd.jsf.vo.SubscribeUrl) responseMessage.getResponse();
//    }
//
//    public java.util.List doRegisterList(java.util.List arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "doRegisterList";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = java.util.List.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return (java.util.List) responseMessage.getResponse();
//    }
//
//    public boolean doCheckRegister(com.jd.jsf.vo.JsfUrl arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "doCheckRegister";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = com.jd.jsf.vo.JsfUrl.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return responseMessage.getResponse() == null ? false : ((Boolean) responseMessage.getResponse()).booleanValue();
//    }
//
//    public boolean doCheckUnRegister(com.jd.jsf.vo.JsfUrl arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "doCheckUnRegister";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = com.jd.jsf.vo.JsfUrl.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return responseMessage.getResponse() == null ? false : ((Boolean) responseMessage.getResponse()).booleanValue();
//    }
//
//    public boolean doUnRegisterList(java.util.List arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "doUnRegisterList";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = java.util.List.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return responseMessage.getResponse() == null ? false : ((Boolean) responseMessage.getResponse()).booleanValue();
//    }
//
//    public java.util.List lookupList(java.util.List arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "lookupList";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = java.util.List.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return (java.util.List) responseMessage.getResponse();
//    }
//
//    public com.jd.jsf.vo.HbResult doHeartbeat(com.jd.jsf.vo.Heartbeat arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "doHeartbeat";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = com.jd.jsf.vo.Heartbeat.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return (com.jd.jsf.vo.HbResult) responseMessage.getResponse();
//    }
//
//    public java.util.List getConfigList(java.util.List arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "getConfigList";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = java.util.List.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return (java.util.List) responseMessage.getResponse();
//    }
//
//    public com.jd.jsf.vo.JsfUrl getConfig(com.jd.jsf.vo.JsfUrl arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "getConfig";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = com.jd.jsf.vo.JsfUrl.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return (com.jd.jsf.vo.JsfUrl) responseMessage.getResponse();
//    }
//
//    public com.jd.jsf.vo.JsfUrl lookupServiceInsAttrs(com.jd.jsf.vo.JsfUrl arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "lookupServiceInsAttrs";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = com.jd.jsf.vo.JsfUrl.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return (com.jd.jsf.vo.JsfUrl) responseMessage.getResponse();
//    }
//
//    public java.util.List lookupServiceInsAttrsList(java.util.List arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "lookupServiceInsAttrsList";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = java.util.List.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return (java.util.List) responseMessage.getResponse();
//    }
//
//    public com.jd.jsf.vo.JsfUrl doRegister(com.jd.jsf.vo.JsfUrl arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "doRegister";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = com.jd.jsf.vo.JsfUrl.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return (com.jd.jsf.vo.JsfUrl) responseMessage.getResponse();
//    }
//
//    public com.jd.jsf.vo.JsfUrl subscribeConfig(com.jd.jsf.vo.JsfUrl arg0, com.jd.jsf.gd.transport.Callback arg1) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "subscribeConfig";
//        Class[] paramTypes = new Class[2];
//        Object[] paramValues = new Object[2];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = com.jd.jsf.vo.JsfUrl.class;
//        paramValues[1] = ($w) $2;
//        paramTypes[1] = com.jd.jsf.gd.transport.Callback.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return (com.jd.jsf.vo.JsfUrl) responseMessage.getResponse();
//    }
//
//    public boolean doUnRegister(com.jd.jsf.vo.JsfUrl arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "doUnRegister";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = com.jd.jsf.vo.JsfUrl.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz,
//                methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return responseMessage.getResponse() == null ? false : ((Boolean) responseMessage.getResponse()).booleanValue();
//    }
//
//    public com.jd.jsf.vo.SubscribeUrl doSubscribe(com.jd.jsf.vo.JsfUrl arg0, com.jd.jsf.gd.transport.Callback arg1) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "doSubscribe";
//        Class[] paramTypes = new Class[2];
//        Object[] paramValues = new Object[2];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = com.jd.jsf.vo.JsfUrl.class;
//        paramValues[1] = ($w) $2;
//        paramTypes[1] = com.jd.jsf.gd.transport.Callback.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz, methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return (com.jd.jsf.vo.SubscribeUrl) responseMessage.getResponse();
//    }
//
//    public boolean doUnSubscribe(com.jd.jsf.vo.JsfUrl arg0) throws com.jd.jsf.gd.error.RpcException {
//        Class clazz = com.jd.jsf.service.RegistryService.class;
//        String methodName = "doUnSubscribe";
//        Class[] paramTypes = new Class[1];
//        Object[] paramValues = new Object[1];
//        paramValues[0] = ($w) $1;
//        paramTypes[0] = com.jd.jsf.vo.JsfUrl.class;
//        com.jd.jsf.gd.msg.RequestMessage requestMessage = com.jd.jsf.gd.msg.MessageBuilder.buildRequest(clazz, methodName, paramTypes, paramValues);
//        com.jd.jsf.gd.msg.ResponseMessage responseMessage = proxyInvoker.invoke(requestMessage);
//        if (responseMessage.isError()) {
//            throw responseMessage.getException();
//        }
//        return responseMessage.getResponse() == null ? false : ((Boolean) responseMessage.getResponse()).booleanValue();
//    }
//}
