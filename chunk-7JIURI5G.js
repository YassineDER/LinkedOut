import{l as q}from"./chunk-ZEJLM2RJ.js";import{a as f}from"./chunk-AQQ4KSDE.js";import{a as re}from"./chunk-IYJKCUOT.js";import{a as ie,c as ne}from"./chunk-FEXGH3DM.js";import{a as Z,c as ee}from"./chunk-QE75UQIF.js";import{a as h}from"./chunk-LX32ZRXP.js";import{$ as u,Ab as te,C as U,D as b,Ea as G,F as H,Fa as l,G as x,Ga as A,Ha as M,I as d,J as E,Ma as w,Oa as W,Pa as Y,U as B,Xa as z,Za as C,_ as s,ab as $,d as D,db as J,ea as g,fb as I,ga as p,hb as X,oa as o,pa as a,qa as m,ra as R,sa as _,u as T,ub as K,vb as V,wa as O,wb as P,xa as v,xb as Q,yb as N,zb as j}from"./chunk-YRSYAIVJ.js";function fe(t,e){if(t&1&&(o(0,"p",35),l(1),a()),t&2){let c=v();s(),A(c.user.description)}}function he(t,e){if(t&1&&(o(0,"p",35),l(1),a()),t&2){let c=v();s(),A(c.user.title)}}var ge=t=>({"sticky-nav":t}),se=(()=>{let e=class e{constructor(r,n,i){this.auth=r,this.router=n,this.users=i,this.isSticky=!1,this.Path=j}onWindowScroll(){this.isSticky=window.scrollY>0}disconnect(){this.auth.logout(),this.router.navigate([j.HOME_LOGIN.toString()])}};e.\u0275fac=function(n){return new(n||e)(u(f),u(V),u(h))},e.\u0275cmp=d({type:e,selectors:[["app-nav"]],hostBindings:function(n,i){n&1&&O("scroll",function(){return i.onWindowScroll()},!1,B)},inputs:{user:"user"},decls:54,vars:19,consts:[[1,"navbar","border","bg-white","p-0","shadow-sm",3,"ngClass"],[1,"navbar-start","w-full"],[3,"routerLink"],["alt","LinkedOut Logo","height","500","priority","","width","500","ngSrc","https://ax0judwwk3y8.objectstorage.eu-paris-1.oci.customer-oci.com/n/ax0judwwk3y8/b/static/o/logo.png",1,"w-11","h-11","mx-4"],["id","search",1,"input","input-sm","input-bordered","flex","items-center","gap-2"],["type","text","placeholder","Rechercher",1,"grow"],[1,"bi","bi-search"],[1,"navbar-end","space-x-5","p-2","w-full"],["routerLinkActive","font-bold border-b-2 border-primary",1,"text-gray-800","flex","flex-col","items-center",3,"routerLink"],[1,"bi","bi-house-door-fill"],["href","#","routerLinkActive","font-bold",1,"text-gray-800","flex","flex-col","items-center",3,"routerLink"],[1,"bi","bi-briefcase-fill"],["routerLinkActive","font-bold",1,"text-gray-800","flex","flex-col","items-center",3,"routerLink"],[1,"bi","bi-chat-left-text-fill"],[1,"bi","bi-people-fill"],[1,"bi","bi-bell-fill"],["id","profile",1,"dropdown","dropdown-end"],["tabindex","0",1,"flex","overflow-hidden","btn","leading-none","px-0.5","btn-ghost"],["alt","Profile Picture","height","64","width","64",1,"rounded-full","w-10",3,"appSrc"],[1,"me-2"],[1,"text-gray-700"],[1,"text-sm","text-gray-500","ms-0"],[1,"bi","bi-chevron-down","mx-1"],["tabindex","0",1,"mt-3","z-[1]","p-2","shadow","card","glass","bg-white","dropdown-content","rounded-box","w-72"],["alt","Profile pic","width","100","height","150",1,"rounded-full","mx-auto",3,"appSrc"],[1,"card-body","items-center","text-center","py-0"],[1,"text-primary","card-title","font-bold",3,"routerLink"],["class","text-sm",4,"ngIf"],[1,"mt-2"],[1,"menu","menu-sm"],["routerLinkActive","active",3,"routerLink"],[1,"bi","bi-person-fill"],[1,"bi","bi-gear-fill"],[3,"click"],[1,"bi","bi-box-arrow-right"],[1,"text-sm"]],template:function(n,i){n&1&&(o(0,"nav",0)(1,"div",1)(2,"a",2),m(3,"img",3),a(),o(4,"label",4),m(5,"input",5)(6,"i",6),a()(),o(7,"div",7)(8,"a",8),m(9,"i",9),l(10,"Accueil"),a(),o(11,"a",10),m(12,"i",11),l(13,"Emplois"),a(),o(14,"a",12),m(15,"i",13),l(16,"Messagerie"),a(),o(17,"a",12),m(18,"i",14),l(19,"R\xE9seau"),a(),o(20,"a",12),m(21,"i",15),l(22,"Notifications "),a(),o(23,"div",16)(24,"div",17),m(25,"img",18),o(26,"div",19)(27,"p",20),l(28),a(),o(29,"p",21),l(30,"Vous"),m(31,"i",22),a()()(),o(32,"ul",23)(33,"a",2),m(34,"img",24),a(),o(35,"div",25)(36,"a",26),l(37),a(),g(38,fe,2,1,"p",27)(39,he,2,1,"p",27),a(),m(40,"hr",28),o(41,"ul",29)(42,"li")(43,"a",30),m(44,"i",31),l(45,"Profil"),a()(),o(46,"li")(47,"a",30),m(48,"i",32),l(49,"Pr\xE9f\xE9rences"),a()(),o(50,"li")(51,"a",33),O("click",function(){return i.disconnect()}),m(52,"i",34),l(53,"Se d\xE9connecter"),a()()()()()()()),n&2&&(p("ngClass",w(17,ge,i.isSticky)),s(2),p("routerLink",i.Path.HOME.toString()),s(6),p("routerLink",i.Path.FEED.toString()),s(3),p("routerLink",i.Path.JOBS.toString()),s(3),p("routerLink",i.Path.MESSAGES.toString()),s(3),p("routerLink",i.Path.NETWORK.toString()),s(3),p("routerLink",i.Path.NOTIFICATIONS.toString()),s(5),p("appSrc",i.user.imageName),s(3),M("",i.user.username," "),s(5),p("routerLink",i.Path.PROFILE.toString()),s(),p("appSrc",i.user.imageName),s(2),p("routerLink",i.Path.PROFILE.toString()),s(),M(" ",i.user.username," "),s(),p("ngIf",i.users.isCompany(i.user)),s(),p("ngIf",i.users.isJobseeker(i.user)||i.users.isAdmin(i.user)),s(4),p("routerLink",i.Path.PROFILE.toString()),s(4),p("routerLink",i.Path.PREFERENCES.toString()))},dependencies:[z,C,P,Q,I,ie],styles:["#profile[_ngcontent-%COMP%]   .btn[_ngcontent-%COMP%]{text-align:left!important;justify-content:left!important}.sticky-nav[_ngcontent-%COMP%]{position:fixed;top:0;width:100%;z-index:1000;animation:_ngcontent-%COMP%_slideDown .4s ease-in-out}@keyframes _ngcontent-%COMP%_slideDown{0%{transform:translateY(-100%)}to{transform:translateY(0)}}#search[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]{transition:width .4s ease-in-out!important;width:30%!important}#search[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]:focus{width:50%!important}"]});let t=e;return t})();var me=(()=>{let e=class e{};e.\u0275fac=function(n){return new(n||e)},e.\u0275cmp=d({type:e,selectors:[["app-footer"]],decls:22,vars:0,consts:[[1,"footer","p-4","bg-blue-50","w-100","left-0","bottom-0"],[1,"flex","justify-around","space-x-2"],["alt","LinkedOut Logo","height","400","ngSrc","https://ax0judwwk3y8.objectstorage.eu-paris-1.oci.customer-oci.com/n/ax0judwwk3y8/b/static/o/logo.png","priority","","width","400",1,"w-1/6","mr-4"],[1,"text-gray-700"],["href","https://github.com/YassineDER","target","_blank",1,"text-blue-700"],[1,"footer-title"],[1,"flex","flex-col","gap-2"],["routerLink","/privacy-policy",1,"link-primary"],["routerLink","/terms-and-conditions",1,"link-primary"]],template:function(n,i){n&1&&(o(0,"footer",0)(1,"aside",1),m(2,"img",2),o(3,"p",3),l(4,"2024 LinkedOut "),m(5,"br"),l(6," Made with \u2764\uFE0F by "),o(7,"a",4),l(8,"Yassine DERGAOUI"),a(),m(9,"br")(10,"br"),o(11,"strong"),l(12," Avis de non-responsabilit\xE9: "),a(),l(13," Cette application est un clone cr\xE9\xE9 \xE0 des fins \xE9ducatives uniquement et n'est ni affili\xE9e ni approuv\xE9e par le site Web d'origine. Aucune donn\xE9e utilisateur n'est collect\xE9e. Tout le code a \xE9t\xE9 \xE9crit par le d\xE9veloppeur. "),a()(),o(14,"div")(15,"h4",5),l(16,"Links"),a(),o(17,"div",6)(18,"a",7),l(19,"Privacy Policy"),a(),o(20,"a",8),l(21,"Terms of Service"),a()()()())},dependencies:[P,I]});let t=e;return t})();function ye(t,e){if(t&1&&(R(0),m(1,"app-nav",1),o(2,"div"),m(3,"router-outlet",null,2),a(),m(5,"app-footer"),_()),t&2){let c=e.ngIf,r=G(4),n=v(2);s(),p("user",c),s(),p("@fadeInUpAnimation",n.prepareOutlet(r))}}function be(t,e){if(t&1&&(R(0),g(1,ye,6,2,"ng-container",0),_()),t&2){let c=e.ngIf;s(),p("ngIf",c.user)}}var xe=t=>({user:t}),le=(()=>{let e=class e{constructor(r,n){this.auth=r,this.users=n,this.user$=this.auth.getAuthenticatedUser().pipe(T(1))}ngOnInit(){this.user$.subscribe(r=>{r&&this.users.changeUser(r)})}prepareOutlet(r){return r&&r.activatedRouteData&&r.activatedRouteData.animation}};e.\u0275fac=function(n){return new(n||e)(u(f),u(h))},e.\u0275cmp=d({type:e,selectors:[["app-home-component"]],decls:2,vars:5,consts:[[4,"ngIf"],[3,"user"],["outlet","outlet"]],template:function(n,i){n&1&&(g(0,be,2,1,"ng-container",0),W(1,"async")),n&2&&p("ngIf",w(3,xe,Y(1,1,i.user$)))},dependencies:[C,K,se,me,$],data:{animation:[q]}});let t=e;return t})();var k=(()=>{let e=class e{constructor(r){this.http=r,this.api=te.hostUrl,this.OCI_API="https://objectstorage.eu-paris-1.oraclecloud.com"}get OCI_URL(){return this.OCI_API}fetchPAR(){return new Promise((r,n)=>{this.http.get(this.api+"/api/storage/generate-par").subscribe({next:i=>r(i),error:i=>n(i)})})}uploadImage(r){}};e.\u0275fac=function(n){return new(n||e)(H(X))},e.\u0275prov=U({token:e,factory:e.\u0275fac});let t=e;return t})();var pe=(t,e)=>D(void 0,null,function*(){let c=x(k),r=x(ee),n=x(f),i=localStorage.getItem("PAR"),y=localStorage.getItem("PAR_EXPIRES");if(!i||y&&new Date(y)<new Date){let L=yield c.fetchPAR().catch(F=>{F.status===400?n.logout():r.alert(F.error.error,Z.ERROR)});if(typeof L!="object")return!1;localStorage.setItem("PAR",c.OCI_URL+L.par_URL),localStorage.setItem("PAR_EXPIRES",r.formatDate(L.expires).toLocaleString())}return!0});var Ee=[{path:"",component:le,canActivate:[pe],children:[{path:"",redirectTo:"social",pathMatch:"full"},{path:"settings",loadChildren:()=>import("./chunk-TIBB57B3.js").then(t=>t.PreferencesModule)},{path:"social",loadChildren:()=>import("./chunk-5R2MECK3.js").then(t=>t.SocialModule)},{path:"out",loadChildren:()=>import("./chunk-RUG7RWSL.js").then(t=>t.ProfileModule)}]}],ce=(()=>{let e=class e{};e.\u0275fac=function(n){return new(n||e)},e.\u0275mod=E({type:e}),e.\u0275inj=b({imports:[N.forChild(Ee),N]});let t=e;return t})();var et=(()=>{let e=class e{};e.\u0275fac=function(n){return new(n||e)},e.\u0275mod=E({type:e}),e.\u0275inj=b({providers:[h,re,k],imports:[J,ce,ne]});let t=e;return t})();export{et as HomeModule};
