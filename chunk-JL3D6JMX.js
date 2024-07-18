import{D as w,l as H}from"./chunk-GSY46XA5.js";import{a as K}from"./chunk-4WVIE5XP.js";import{a as v}from"./chunk-PJMTOKAL.js";import{a as O,b as Y}from"./chunk-LZ2Q336T.js";import{A as F,Aa as T,Ba as D,D as f,E as h,Ia as E,Ka as U,Ma as I,Oa as C,Qa as z,T as a,U as d,Y as x,_ as s,bb as G,cb as $,db as B,eb as J,fb as S,ga as n,ha as r,ia as m,ja as k,ka as P,na as R,oa as g,pa as L,r as N,ra as j,sa as l,ta as y,ua as M,x as _,y as u,ya as A}from"./chunk-V6KQBE6G.js";function re(t,e){if(t&1&&(n(0,"p",34),l(1),r()),t&2){let c=g();a(),y(c.user.admin_title)}}function oe(t,e){if(t&1&&(n(0,"p",34),l(1),r()),t&2){let c=g();a(),y(c.user.description)}}function ae(t,e){if(t&1&&(n(0,"p",34),l(1),r()),t&2){let c=g();a(),y(c.user.title)}}var W=(()=>{let e=class e{constructor(p,i,o){this.auth=p,this.router=i,this.userServ=o,this.Path=O}disconnect(){this.auth.logout(),this.router.navigate([O.HOME_LOGIN.toString()])}};e.\u0275fac=function(i){return new(i||e)(d(w),d($),d(v))},e.\u0275cmp=f({type:e,selectors:[["app-nav"]],inputs:{user:"user"},decls:55,vars:17,consts:[[1,"navbar","border","bg-white","p-0","shadow-sm"],[1,"navbar-start","w-full"],[3,"routerLink"],["alt","LinkedOut Logo","height","500","ngSrc","assets/images/logo.png","priority","","width","500",1,"w-11","h-11","mx-4"],["id","search",1,"input","input-sm","input-bordered","flex","items-center","gap-2"],["type","text","placeholder","Rechercher",1,"grow"],[1,"bi","bi-search"],[1,"navbar-end","space-x-5","p-2","w-full"],["routerLinkActive","font-bold border-b-2 border-primary",1,"text-gray-800","flex","flex-col","items-center",3,"routerLink"],[1,"bi","bi-house-door-fill"],["href","#","routerLinkActive","font-bold",1,"text-gray-800","flex","flex-col","items-center",3,"routerLink"],[1,"bi","bi-briefcase-fill"],["routerLinkActive","font-bold",1,"text-gray-800","flex","flex-col","items-center",3,"routerLink"],[1,"bi","bi-chat-left-text-fill"],[1,"bi","bi-people-fill"],[1,"bi","bi-bell-fill"],["id","profile",1,"dropdown","dropdown-end"],["tabindex","0",1,"flex","overflow-hidden","btn","leading-none","p-0","btn-ghost"],["alt","Profile Picture","height","64","priority","","width","64",1,"rounded-full","w-10",3,"ngSrc"],[1,"me-2"],[1,"text-gray-700"],[1,"text-sm","text-gray-500","ms-0"],[1,"bi","bi-chevron-down","mx-1"],["tabindex","0",1,"mt-3","z-[1]","p-2","shadow","card","glass","dropdown-content","bg-base-100","rounded-box","w-72"],["alt","Profile pic","priority","","width","100","height","150",1,"rounded-full","mx-auto",3,"ngSrc"],[1,"card-body","items-center","text-center","py-0"],[1,"text-primary","card-title","font-bold",3,"routerLink"],["class","text-sm",4,"ngIf"],[1,"mt-2"],[1,"menu","menu-sm"],[1,"bi","bi-person-fill"],[1,"bi","bi-gear-fill"],[3,"click"],[1,"bi","bi-box-arrow-right"],[1,"text-sm"]],template:function(i,o){i&1&&(n(0,"nav",0)(1,"div",1)(2,"a",2),m(3,"img",3),r(),n(4,"label",4),m(5,"input",5)(6,"i",6),r()(),n(7,"div",7)(8,"a",8),m(9,"i",9),l(10,"Accueil"),r(),n(11,"a",10),m(12,"i",11),l(13,"Emplois"),r(),n(14,"a",12),m(15,"i",13),l(16,"Messagerie"),r(),n(17,"a",12),m(18,"i",14),l(19,"R\xE9seau"),r(),n(20,"a",12),m(21,"i",15),l(22,"Notifications "),r(),n(23,"div",16)(24,"div",17),m(25,"img",18),n(26,"div",19)(27,"p",20),l(28),r(),n(29,"p",21),l(30,"Vous"),m(31,"i",22),r()()(),n(32,"ul",23)(33,"a",2),m(34,"img",24),r(),n(35,"div",25)(36,"a",26),l(37),r(),x(38,re,2,1,"p",27)(39,oe,2,1,"p",27)(40,ae,2,1,"p",27),r(),m(41,"hr",28),n(42,"div",29)(43,"li")(44,"a",2),m(45,"i",30),l(46,"Profil"),r()(),n(47,"li")(48,"a",2),m(49,"i",31),l(50,"Pr\xE9f\xE9rences"),r()(),n(51,"li")(52,"a",32),R("click",function(){return o.disconnect()}),m(53,"i",33),l(54,"Se d\xE9connecter"),r()()()()()()()),i&2&&(a(2),s("routerLink",o.Path.HOME.toString()),a(6),s("routerLink",o.Path.FEED.toString()),a(3),s("routerLink",o.Path.JOBS.toString()),a(3),s("routerLink",o.Path.MESSAGES.toString()),a(3),s("routerLink",o.Path.NETWORK.toString()),a(3),s("routerLink",o.Path.NOTIFICATIONS.toString()),a(5),L("ngSrc",o.user.image_url),a(3),M("",o.user.username," "),a(5),s("routerLink",o.Path.PROFILE.toString()),a(),L("ngSrc",o.user.image_url),a(2),s("routerLink",o.Path.PROFILE.toString()),a(),M(" ",o.user.username," "),a(),s("ngIf",o.userServ.isAdmin(o.user)),a(),s("ngIf",o.userServ.isCompany(o.user)),a(),s("ngIf",o.userServ.isJobseeker(o.user)),a(4),s("routerLink",o.Path.PROFILE.toString()),a(4),s("routerLink",o.Path.PREFERENCES.toString()))},dependencies:[E,B,J,C],styles:["#profile[_ngcontent-%COMP%]   .btn[_ngcontent-%COMP%]{text-align:left!important;justify-content:left!important}#search[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]{transition:width .4s ease-in-out!important;width:30%!important}#search[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]:focus{width:50%!important}"]});let t=e;return t})();var q=(()=>{let e=class e{};e.\u0275fac=function(i){return new(i||e)},e.\u0275cmp=f({type:e,selectors:[["app-footer"]],decls:19,vars:0,consts:[[1,"footer","p-4","bg-blue-50","w-100","left-0","bottom-0"],[1,"flex"],["ngSrc","assets/images/logo.png","width","400","height","400","priority","","alt","LinkedOut Logo",1,"w-1/6","mr-4"],[1,"text-gray-700"],["href","https://github.com/YassineDER","target","_blank",1,"text-blue-700"],[1,"footer-title"],[1,"grid","grid-flow-col","text-xl","gap-4"],[1,"bi","bi-twitter-x","text-white","bg-black","p-1","rounded"],[1,"bi","bi-facebook","text-white","bg-blue-700","p-1","rounded"],[1,"bi","bi-instagram","text-white","bg-red-500","p-1","rounded"]],template:function(i,o){i&1&&(n(0,"footer",0)(1,"aside",1),m(2,"img",2),n(3,"p",3),l(4,"\xA9 2024 LinkedOut "),m(5,"br"),l(6," Made with \u2764\uFE0F by "),n(7,"a",4),l(8,"Yassine DERGAOUI"),r()()(),n(9,"div")(10,"h4",5),l(11,"Social"),r(),n(12,"div",6)(13,"a"),m(14,"i",7),r(),n(15,"a"),m(16,"i",8),r(),n(17,"a"),m(18,"i",9),r()()()())},dependencies:[C]});let t=e;return t})();function le(t,e){if(t&1&&(k(0),m(1,"app-nav",1),n(2,"div"),m(3,"router-outlet",null,2),r(),m(5,"app-footer"),P()),t&2){let c=e.ngIf,p=j(4),i=g(2);a(),s("user",c),a(),s("@fadeInUpAnimation",i.prepareOutlet(p))}}function se(t,e){if(t&1&&(k(0),x(1,le,6,2,"ng-container",0),P()),t&2){let c=e.ngIf;a(),s("ngIf",c.user==null?null:c.user[0])}}var ce=t=>({user:t}),Q=(()=>{let e=class e{constructor(p,i){this.auth=p,this.users=i,this.user$=this.auth.getAuthenticatedUser().pipe(N(1))}ngOnInit(){this.user$.subscribe(([p,i])=>{i&&p&&this.users.changeUser(p)})}prepareOutlet(p){return p&&p.activatedRouteData&&p.activatedRouteData.animation}};e.\u0275fac=function(i){return new(i||e)(d(w),d(v))},e.\u0275cmp=f({type:e,selectors:[["app-home"]],decls:2,vars:5,consts:[[4,"ngIf"],[3,"user"],["outlet","outlet"]],template:function(i,o){i&1&&(x(0,se,2,1,"ng-container",0),T(1,"async")),i&2&&s("ngIf",A(3,ce,D(1,1,o.user$)))},dependencies:[E,G,W,q,U],data:{animation:[H]}});let t=e;return t})();var de=[{path:"",component:Q,children:[{path:"",redirectTo:"social",pathMatch:"full"},{path:"preferences",loadChildren:()=>import("./chunk-FCF53ZYX.js").then(t=>t.PreferencesModule)},{path:"social",loadChildren:()=>import("./chunk-KBGPVULW.js").then(t=>t.SocialModule)},{path:"out",loadChildren:()=>import("./chunk-SLGOQBLI.js").then(t=>t.ProfileModule)}]}],X=(()=>{let e=class e{};e.\u0275fac=function(i){return new(i||e)},e.\u0275mod=h({type:e}),e.\u0275inj=u({imports:[S.forChild(de),S]});let t=e;return t})();var Z=(()=>{let e=class e{};e.\u0275fac=function(i){return new(i||e)},e.\u0275mod=h({type:e}),e.\u0275inj=u({imports:[I,S]});let t=e;return t})();var ee=(()=>{let e=class e{constructor(p){this.http=p,this.api=Y.hostUrl+"/api/jobseeker"}};e.\u0275fac=function(i){return new(i||e)(F(z))},e.\u0275prov=_({token:e,factory:e.\u0275fac});let t=e;return t})();var ze=(()=>{let e=class e{};e.\u0275fac=function(i){return new(i||e)},e.\u0275mod=h({type:e}),e.\u0275inj=u({providers:[v,K,ee],imports:[I,X,Z]});let t=e;return t})();export{ze as HomeModule};