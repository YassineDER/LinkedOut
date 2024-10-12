import{l as q}from"./chunk-ZEJLM2RJ.js";import{a as f}from"./chunk-PWSY55TI.js";import{a as ne}from"./chunk-H5QHPP26.js";import{a as h,b as te,d as ie}from"./chunk-PD66CMLE.js";import{c as Z,d as N}from"./chunk-XYBW5LQM.js";import{$ as d,C as T,D as y,Ea as B,F as H,Fa as m,G as b,Ga as R,Ha as A,I as u,J as E,Ma as C,Oa as W,Pa as Y,U as G,Xa as z,Za as w,_ as s,ab as J,d as D,db as $,ea as g,fb as P,ga as p,hb as X,oa as o,pa as a,qa as l,ra as _,sa as M,u as U,ub as K,vb as V,wa as O,wb as k,xa as v,xb as Q,yb as j,zb as ee}from"./chunk-YQE6JNPI.js";function ue(t,e){if(t&1&&(o(0,"p",34),m(1),a()),t&2){let c=v();s(),R(c.user.description)}}function fe(t,e){if(t&1&&(o(0,"p",34),m(1),a()),t&2){let c=v();s(),R(c.user.title)}}var he=t=>({"sticky-nav":t}),ae=(()=>{let e=class e{constructor(r,n,i){this.auth=r,this.router=n,this.users=i,this.isSticky=!1,this.Path=N}onWindowScroll(){this.isSticky=window.scrollY>0}disconnect(){this.auth.logout(),this.router.navigate([N.HOME_LOGIN.toString()])}};e.\u0275fac=function(n){return new(n||e)(d(f),d(V),d(h))},e.\u0275cmp=u({type:e,selectors:[["app-nav"]],hostBindings:function(n,i){n&1&&O("scroll",function(){return i.onWindowScroll()},!1,G)},inputs:{user:"user"},decls:51,vars:18,consts:[[1,"navbar","border","bg-white","p-0","shadow-sm",3,"ngClass"],[1,"navbar-start","w-full"],[3,"routerLink"],["alt","LinkedOut Logo","height","500","priority","","width","500","ngSrc","https://ax0judwwk3y8.objectstorage.eu-paris-1.oci.customer-oci.com/n/ax0judwwk3y8/b/static/o/logo.png",1,"w-11","h-11","mx-4"],["id","search",1,"input","input-sm","input-bordered","flex","items-center","gap-2"],["type","text","placeholder","Rechercher",1,"grow"],[1,"bi","bi-search"],[1,"navbar-end","space-x-5","p-2","w-full"],["routerLinkActive","font-bold border-b-2 border-primary",1,"text-gray-800","flex","flex-col","items-center",3,"routerLink"],[1,"bi","bi-house-door-fill"],["href","#","routerLinkActive","font-bold",1,"text-gray-800","flex","flex-col","items-center",3,"routerLink"],[1,"bi","bi-briefcase-fill"],["routerLinkActive","font-bold",1,"text-gray-800","flex","flex-col","items-center",3,"routerLink"],[1,"bi","bi-chat-left-text-fill"],[1,"bi","bi-people-fill"],["id","profile",1,"dropdown","dropdown-end"],["tabindex","0",1,"flex","overflow-hidden","btn","leading-none","px-0.5","btn-ghost"],["alt","Profile Picture","height","64","width","64",1,"rounded-full","w-10",3,"appSrc"],[1,"me-2"],[1,"text-gray-700"],[1,"text-sm","text-gray-500","ms-0"],[1,"bi","bi-chevron-down","mx-1"],["tabindex","0",1,"mt-3","z-[1]","p-2","shadow","card","glass","bg-white","dropdown-content","rounded-box","w-72"],["alt","Profile pic","width","100","height","150",1,"rounded-full","mx-auto",3,"appSrc"],[1,"card-body","items-center","text-center","py-0"],[1,"text-primary","card-title","font-bold",3,"routerLink"],["class","text-sm",4,"ngIf"],[1,"mt-2"],[1,"menu","menu-sm"],["routerLinkActive","active",3,"routerLink"],[1,"bi","bi-person-fill"],[1,"bi","bi-gear-fill"],[3,"click"],[1,"bi","bi-box-arrow-right"],[1,"text-sm"]],template:function(n,i){n&1&&(o(0,"nav",0)(1,"div",1)(2,"a",2),l(3,"img",3),a(),o(4,"label",4),l(5,"input",5)(6,"i",6),a()(),o(7,"div",7)(8,"a",8),l(9,"i",9),m(10,"Accueil"),a(),o(11,"a",10),l(12,"i",11),m(13,"Emplois"),a(),o(14,"a",12),l(15,"i",13),m(16,"Messagerie"),a(),o(17,"a",12),l(18,"i",14),m(19,"R\xE9seau"),a(),o(20,"div",15)(21,"div",16),l(22,"img",17),o(23,"div",18)(24,"p",19),m(25),a(),o(26,"p",20),m(27,"Vous"),l(28,"i",21),a()()(),o(29,"ul",22)(30,"a",2),l(31,"img",23),a(),o(32,"div",24)(33,"a",25),m(34),a(),g(35,ue,2,1,"p",26)(36,fe,2,1,"p",26),a(),l(37,"hr",27),o(38,"ul",28)(39,"li")(40,"a",29),l(41,"i",30),m(42,"Profil"),a()(),o(43,"li")(44,"a",29),l(45,"i",31),m(46,"Pr\xE9f\xE9rences"),a()(),o(47,"li")(48,"a",32),O("click",function(){return i.disconnect()}),l(49,"i",33),m(50,"Se d\xE9connecter"),a()()()()()()()),n&2&&(p("ngClass",C(16,he,i.isSticky)),s(2),p("routerLink",i.Path.HOME.toString()),s(6),p("routerLink",i.Path.FEED.toString()),s(3),p("routerLink",i.Path.JOBS.toString()),s(3),p("routerLink",i.Path.MESSAGING.toString()),s(3),p("routerLink",i.Path.NETWORK.toString()),s(5),p("appSrc",i.user.imageName),s(3),A("",i.user.username," "),s(5),p("routerLink",i.Path.PROFILE.toString()),s(),p("appSrc",i.user.imageName),s(2),p("routerLink",i.Path.PROFILE.toString()),s(),A(" ",i.user.username," "),s(),p("ngIf",i.users.isCompany(i.user)),s(),p("ngIf",i.users.isJobseeker(i.user)||i.users.isAdmin(i.user)),s(4),p("routerLink",i.Path.PROFILE.toString()),s(4),p("routerLink",i.Path.PREFERENCES.toString()))},dependencies:[z,w,k,Q,P,te],styles:["#profile[_ngcontent-%COMP%]   .btn[_ngcontent-%COMP%]{text-align:left!important;justify-content:left!important}.sticky-nav[_ngcontent-%COMP%]{position:fixed;top:0;width:100%;z-index:1000;animation:_ngcontent-%COMP%_slideDown .4s ease-in-out}@keyframes _ngcontent-%COMP%_slideDown{0%{transform:translateY(-100%)}to{transform:translateY(0)}}#search[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]{transition:width .4s ease-in-out!important;width:30%!important}#search[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]:focus{width:50%!important}"]});let t=e;return t})();var se=(()=>{let e=class e{};e.\u0275fac=function(n){return new(n||e)},e.\u0275cmp=u({type:e,selectors:[["app-footer"]],decls:22,vars:0,consts:[[1,"footer","p-4","bg-blue-50","w-100","left-0","bottom-0"],[1,"flex","justify-around","space-x-2"],["alt","LinkedOut Logo","height","400","ngSrc","https://ax0judwwk3y8.objectstorage.eu-paris-1.oci.customer-oci.com/n/ax0judwwk3y8/b/static/o/logo.png","priority","","width","400",1,"w-1/6","mr-4"],[1,"text-gray-700"],["href","https://github.com/YassineDER","target","_blank",1,"text-blue-700"],[1,"footer-title"],[1,"flex","flex-col","gap-2"],["routerLink","/privacy-policy",1,"link-primary"],["routerLink","/terms-and-conditions",1,"link-primary"]],template:function(n,i){n&1&&(o(0,"footer",0)(1,"aside",1),l(2,"img",2),o(3,"p",3),m(4,"2024 LinkedOut "),l(5,"br"),m(6," Made with \u2764\uFE0F by "),o(7,"a",4),m(8,"Yassine DERGAOUI"),a(),l(9,"br")(10,"br"),o(11,"strong"),m(12," Avis de non-responsabilit\xE9: "),a(),m(13," Cette application est un clone cr\xE9\xE9 \xE0 des fins \xE9ducatives uniquement et n'est ni affili\xE9e ni approuv\xE9e par le site Web d'origine. Aucune donn\xE9e utilisateur n'est collect\xE9e. Tout le code a \xE9t\xE9 \xE9crit par le d\xE9veloppeur. "),a()(),o(14,"div")(15,"h4",5),m(16,"Links"),a(),o(17,"div",6)(18,"a",7),m(19,"Privacy Policy"),a(),o(20,"a",8),m(21,"Terms of Service"),a()()()())},dependencies:[k,P]});let t=e;return t})();function Se(t,e){if(t&1&&(_(0),o(1,"div",1),l(2,"app-nav",2),o(3,"div"),l(4,"router-outlet",null,3),a(),l(6,"app-footer",4),a(),M()),t&2){let c=e.ngIf,r=B(5),n=v(2);s(2),p("user",c),s(),p("@fadeInUpAnimation",n.prepareOutlet(r))}}function xe(t,e){if(t&1&&(_(0),g(1,Se,7,2,"ng-container",0),M()),t&2){let c=e.ngIf;s(),p("ngIf",c.user)}}var ye=t=>({user:t}),le=(()=>{let e=class e{constructor(r,n){this.auth=r,this.users=n,this.user$=this.auth.getAuthenticatedUser().pipe(U(1))}ngOnInit(){this.user$.subscribe(r=>{r&&this.users.changeUser(r)})}prepareOutlet(r){return r&&r.activatedRouteData&&r.activatedRouteData.animation}};e.\u0275fac=function(n){return new(n||e)(d(f),d(h))},e.\u0275cmp=u({type:e,selectors:[["app-home-component"]],decls:2,vars:5,consts:[[4,"ngIf"],[1,"flex","flex-col","min-h-dvh","home-container"],[3,"user"],["outlet","outlet"],[1,"flex-shrink-0"]],template:function(n,i){n&1&&(g(0,xe,2,1,"ng-container",0),W(1,"async")),n&2&&p("ngIf",C(3,ye,Y(1,1,i.user$)))},dependencies:[w,K,ae,se,J],styles:[".home-container[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]{flex-shrink:0}.home-container[_ngcontent-%COMP%] > div[_ngcontent-%COMP%]{flex-grow:1}"],data:{animation:[q]}});let t=e;return t})();var I=(()=>{let e=class e{constructor(r){this.http=r,this.api=ee.hostUrl,this.OCI_API="https://objectstorage.eu-paris-1.oraclecloud.com"}get OCI_URL(){return this.OCI_API}fetchPAR(){return new Promise((r,n)=>{this.http.get(this.api+"/api/storage/generate-par").subscribe({next:i=>r(i),error:i=>n(i)})})}uploadImage(r){}};e.\u0275fac=function(n){return new(n||e)(H(X))},e.\u0275prov=T({token:e,factory:e.\u0275fac});let t=e;return t})();var me=(t,e)=>D(void 0,null,function*(){let c=b(I),r=b(Z),n=b(f),i=localStorage.getItem("PAR"),x=localStorage.getItem("PAR_EXPIRES");if(!i||x&&new Date(x)<new Date){let L=yield c.fetchPAR().catch(F=>{F.status===400?n.logout():r.alert(F.error.error)});if(typeof L!="object")return!1;localStorage.setItem("PAR",c.OCI_URL+L.par_URL),localStorage.setItem("PAR_EXPIRES",r.formatDate(L.expires).toLocaleString())}return!0});var be=[{path:"",component:le,canActivate:[me],children:[{path:"",redirectTo:"social",pathMatch:"full"},{path:"preferences",loadChildren:()=>import("./chunk-SNEC7KCF.js").then(t=>t.PreferencesModule)},{path:"social",loadChildren:()=>import("./chunk-MV23VOJX.js").then(t=>t.SocialModule)},{path:"out",loadChildren:()=>import("./chunk-BK22Z7GU.js").then(t=>t.ProfileModule)},{path:"messaging",loadChildren:()=>import("./chunk-C5BC6UJE.js").then(t=>t.MessagingModule)},{path:"jobs",loadChildren:()=>import("./chunk-HW2J4RU2.js").then(t=>t.JobsModule)}]}],pe=(()=>{let e=class e{};e.\u0275fac=function(n){return new(n||e)},e.\u0275mod=E({type:e}),e.\u0275inj=y({imports:[j.forChild(be),j]});let t=e;return t})();var Qe=(()=>{let e=class e{};e.\u0275fac=function(n){return new(n||e)},e.\u0275mod=E({type:e}),e.\u0275inj=y({providers:[h,ne,I],imports:[$,pe,ie]});let t=e;return t})();export{Qe as HomeModule};
