import{c as S,e as j,f as a}from"./chunk-VCVIXHJE.js";import{$ as s,C as u,D as l,F as f,J as h,K as o,M as d,X as n,Z as g,db as v,g as m,hb as E,yb as I}from"./chunk-L2MPYLPP.js";var x=(()=>{let t=class t{constructor(e){this.http=e,this.api=j.hostUrl+"/api/user",this.userSource=new m(null),this.currentUser=this.userSource.asObservable()}isJobseeker(e){return e.role.name===a.JOBSEEKER.toString()}isCompany(e){return e.role.name===a.COMPANY.toString()}isAdmin(e){return e.role.name===a.ADMIN.toString()}changeUser(e){this.userSource.next(e)}suggestUsers(){return new Promise((e,i)=>{this.http.get(this.api+"/suggested",{params:{page:"0",size:"6"}}).subscribe({next:c=>e(c.content),error:c=>i(c.error.error)})})}};t.\u0275fac=function(i){return new(i||t)(f(E))},t.\u0275prov=u({token:t,factory:t.\u0275fac});let r=t;return r})();var O=(()=>{let t=class t{constructor(e,i){this.el=e,this.utils=i}ngOnChanges(e){e.appElapsedDate&&(this.el.nativeElement.textContent=this.utils.getElapsedTime(this.appElapsedDate))}};t.\u0275fac=function(i){return new(i||t)(s(n),s(S))},t.\u0275dir=o({type:t,selectors:[["","appElapsedDate",""]],inputs:{appElapsedDate:"appElapsedDate"},features:[d]});let r=t;return r})();var N=(()=>{let t=class t{constructor(e,i){this.el=e,this.renderer=i,this.PAR=localStorage.getItem("PAR")||""}ngOnInit(){let e=this.PAR+this.appSrc;this.renderer.setAttribute(this.el.nativeElement,"src",e)}};t.\u0275fac=function(i){return new(i||t)(s(n),s(g))},t.\u0275dir=o({type:t,selectors:[["","appSrc",""]],inputs:{appSrc:"appSrc"}});let r=t;return r})();var H=(()=>{let t=class t{};t.\u0275fac=function(i){return new(i||t)},t.\u0275mod=h({type:t}),t.\u0275inj=l({imports:[v,I]});let r=t;return r})();export{x as a,N as b,O as c,H as d};
