import{l as A}from"./chunk-ZEJLM2RJ.js";import{a as O,b as R,c as T,e as w}from"./chunk-RQCKZQNO.js";import{d as D}from"./chunk-ZCGLQAZY.js";import{a as s}from"./chunk-QGG2LZ3E.js";import{C as v,D as a,Ga as N,Ia as U,La as _,S as p,T as f,X as l,Z as r,ab as F,ea as C,eb as h,fa as y,ga as m,ha as u,ia as d,ma as I,oa as M,q as g,va as S,x as n,xa as j,ya as x}from"./chunk-JPZ7LBG3.js";function B(t,e){if(t&1&&(u(0),m(1,"app-nav",1),C(2,"div"),m(3,"router-outlet",null,2),y(),m(5,"app-footer"),d()),t&2){let c=e.ngIf,i=M(4),o=I(2);p(),r("user",c),p(),r("@fadeInUpAnimation",o.prepareOutlet(i))}}function H(t,e){if(t&1&&(u(0),l(1,B,6,2,"ng-container",0),d()),t&2){let c=e.ngIf;p(),r("ngIf",c.user)}}var k=t=>({user:t}),P=(()=>{let e=class e{constructor(i,o){this.auth=i,this.users=o,this.user$=this.auth.getAuthenticatedUser().pipe(g(1))}ngOnInit(){this.user$.subscribe(i=>{i&&this.users.changeUser(i)})}prepareOutlet(i){return i&&i.activatedRouteData&&i.activatedRouteData.animation}};e.\u0275fac=function(o){return new(o||e)(f(D),f(s))},e.\u0275cmp=v({type:e,selectors:[["app-home-component"]],decls:2,vars:5,consts:[[4,"ngIf"],[3,"user"],["outlet","outlet"]],template:function(o,b){o&1&&(l(0,H,2,1,"ng-container",0),j(1,"async")),o&2&&r("ngIf",S(3,k,x(1,1,b.user$)))},dependencies:[N,F,O,R,U],data:{animation:[A]}});let t=e;return t})();var q=[{path:"",component:P,children:[{path:"",redirectTo:"social",pathMatch:"full"},{path:"preferences",loadChildren:()=>import("./chunk-D5QVJRJ6.js").then(t=>t.PreferencesModule)},{path:"social",loadChildren:()=>import("./chunk-S27YKKRQ.js").then(t=>t.SocialModule)},{path:"out",loadChildren:()=>import("./chunk-GMKDIIIN.js").then(t=>t.ProfileModule)}]}],$=(()=>{let e=class e{};e.\u0275fac=function(o){return new(o||e)},e.\u0275mod=a({type:e}),e.\u0275inj=n({imports:[h.forChild(q),h]});let t=e;return t})();var me=(()=>{let e=class e{};e.\u0275fac=function(o){return new(o||e)},e.\u0275mod=a({type:e}),e.\u0275inj=n({providers:[s,T],imports:[_,$,w]});let t=e;return t})();export{me as HomeModule};
