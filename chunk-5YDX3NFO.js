import{a as T,b as y}from"./chunk-DYD222H6.js";import{a as I}from"./chunk-SRDH6GZZ.js";import{A as V,D as u,E as M,Ga as R,I as E,J as _,Ma as $,O as U,Oa as C,Qa as B,T as s,U as f,Y as b,_ as c,ca as h,db as O,ea as z,fa as q,fb as j,ga as t,ha as i,ia as p,la as w,na as S,oa as d,pa as J,sa as r,ta as x,ua as v,va as k,x as N,y as D}from"./chunk-V6KQBE6G.js";var oe=(n,e)=>e.user_id;function ae(n,e){if(n&1){let l=w();t(0,"button",8),S("click",function(){E(l);let o=d().$implicit,m=d();return _(m.social.connect(o))}),r(1,"Se connecter"),i()}}function se(n,e){if(n&1){let l=w();t(0,"button",9),S("click",function(){E(l);let o=d().$implicit,m=d();return _(m.social.triggerMsg(o))}),r(1,"Message"),i()}}function le(n,e){if(n&1&&(t(0,"div",2),p(1,"img",3),t(2,"div")(3,"h5",4),r(4),i(),t(5,"p",5),r(6),i()()(),t(7,"div",6),b(8,ae,2,0,"button",7)(9,se,2,0),i()),n&2){let l=e.$implicit,a=d();s(),J("ngSrc",l.image_url),s(3),k("",l.first_name," ",l.last_name,""),s(2),x(l.title?l.title:"Nouveau sur LinkedOut"),s(2),h(8,a.social.isConnection(a.user,l)?9:8)}}var H=(()=>{let e=class e{constructor(a,o){this.social=a,this.users=o,this.profiles=[]}ngOnInit(){this.users.suggestJobseekers().subscribe(a=>{this.profiles=a})}};e.\u0275fac=function(o){return new(o||e)(f(y),f(T))},e.\u0275cmp=u({type:e,selectors:[["app-users-suggestions"]],inputs:{user:"user"},decls:5,vars:0,consts:[[1,"rounded-lg","bg-white","shadow","p-4"],[1,"font-semibold","text-xl","mb-6"],[1,"flex","items-center","my-2"],["width","64","height","64","priority","","alt","Profile pic",1,"w-10","h-10","rounded-full","mr-3",3,"ngSrc"],[1,"text-sm"],[1,"text-xs","text-gray-500"],[1,"w-full","text-end","mb-4"],["class","btn btn-outline btn-primary btn-sm"],[1,"btn","btn-outline","btn-primary","btn-sm",3,"click"],[1,"btn","btn-primary","btn-sm",3,"click"]],template:function(o,m){o&1&&(t(0,"div",0)(1,"h1",1),r(2,"Autres profils similaires"),i(),z(3,le,10,5,null,null,oe),i()),o&2&&(s(3),q(m.profiles))},dependencies:[C]});let n=e;return n})();function ce(n,e){if(n&1&&(t(0,"div",11)(1,"h2",12),r(2),i(),t(3,"div",13),p(4,"i",14),r(5),i()(),t(6,"p",15),r(7),i()),n&2){let l=d();s(2),k("",l.user.first_name," ",l.user.last_name,""),s(3),v(" ",l.user.address," "),s(2),x(l.user.title)}}function de(n,e){if(n&1&&(t(0,"div",11)(1,"h2",16),r(2),i(),t(3,"div",13),p(4,"i",17),r(5),i()(),t(6,"p",18),r(7),i(),t(8,"div",19),p(9,"i",20),t(10,"a",21),r(11),i()()),n&2){let l=d();s(2),x(l.user.company_name),s(3),v(" ",l.user.headquarters," "),s(2),x(l.user.sector),s(3),c("href",l.user.website,U),s(),x(l.user.website)}}function ue(n,e){if(n&1&&(t(0,"div",11)(1,"h2",16),r(2),t(3,"span",22),r(4,"staff"),i()(),t(5,"div",13),p(6,"i",23),r(7),i()(),t(8,"p",18),r(9),i()),n&2){let l=d();s(2),k("",l.user.first_name," ",l.user.last_name," "),s(5),v(" ",l.user.email," "),s(2),x(l.user.admin_title)}}var K=(()=>{let e=class e{constructor(a,o){this.users=a,this.social=o,this.Path=I}};e.\u0275fac=function(o){return new(o||e)(f(T),f(y))},e.\u0275cmp=u({type:e,selectors:[["app-overview-profile"]],inputs:{user:"user"},decls:17,vars:4,consts:[[1,"bg-white","shadow","rounded","overflow-hidden"],[1,"relative","mb-2"],["alt","Banner Image","height","396","ngSrc","assets/images/profile_banner.png","priority","","width","1584",1,"w-full","h-32","object-cover"],[1,"rounded-full","border-4","border-white","absolute","left-5","transform","-bottom-16"],[1,"w-24","h-22","bg-white","rounded-full","flex","items-start","justify-start"],["alt","Profile Image","height","96","priority","","width","96",1,"w-full","h-full","rounded-full",3,"ngSrc"],[1,"absolute","top-0","right-0","m-2"],[1,"text-white","text-xl","bg-transparent","hover:bg-gray-500","rounded-full","px-2","py-1"],[1,"bi","bi-pencil-square"],[1,"p-3","mx-2"],[1,"link","link-primary","mt-2",3,"routerLink"],[1,"flex","items-center","justify-between"],[1,"text-2xl","font-bold"],[1,"me-3"],[1,"bi","bi-geo-alt-fill","text-primary","text-lg"],[1,"text-gray-600","text-sm"],[1,"text-xl","font-bold"],[1,"bi","bi-geo-alt-fill","text-primary","text-lg","me-2"],[1,"text-gray-600"],[1,"text-sm","text-gray-600","flex","items-center"],[1,"bi","bi-box-arrow-up-right","text-lg","me-2"],["target","_blank",1,"link","link-primary",3,"href"],[1,"badge","badge-info"],[1,"bi","bi-envelope-fill","color-primary","text-lg","me-2"]],template:function(o,m){o&1&&(t(0,"div",0)(1,"div",1),p(2,"img",2),t(3,"div",3)(4,"div",4),p(5,"img",5),i()(),t(6,"div",6)(7,"button",7),p(8,"i",8),i()()(),p(9,"br")(10,"br"),t(11,"div",9),b(12,ce,8,4)(13,de,12,5)(14,ue,10,4),t(15,"a",10),r(16),i()()()),o&2&&(s(5),c("ngSrc",m.user.image_url),s(7),h(12,m.users.isJobseeker(m.user)?12:m.users.isCompany(m.user)?13:m.users.isAdmin(m.user)?14:-1),s(3),c("routerLink",m.Path.NETWORK.toString()),s(),v("",m.social.profile_stats==null?null:m.social.profile_stats.connections," connections"))},dependencies:[O,C]});let n=e;return n})();var Q=(()=>{let e=class e{constructor(a){this.social=a,this.profile_views=0,this.today_views=0,this.search_apperances=0}ngOnInit(){}};e.\u0275fac=function(o){return new(o||e)(f(y))},e.\u0275cmp=u({type:e,selectors:[["app-stats-profile"]],inputs:{user:"user"},decls:21,vars:3,consts:[[1,"p-4","bg-white","shadow","rounded-lg"],[1,"font-semibold","text-xl"],[1,"text-gray-500","text-sm","mb-3"],[1,"bi","bi-eye-fill"],[1,"my-4"],[1,"text-5xl","font-bold","text-primary"],[1,"text-gray-500"]],template:function(o,m){o&1&&(t(0,"div",0)(1,"h1",1),r(2,"Tableau de bord"),i(),t(3,"span",2),p(4,"i",3),r(5,"Priv\xE9 pour vous "),i(),t(6,"div",4)(7,"div",5),r(8),i(),t(9,"div",6),r(10,"Vues totales"),i()(),t(11,"div",4)(12,"div",5),r(13),i(),t(14,"div",6),r(15,"Vues aujourd'hui"),i()(),t(16,"div",4)(17,"div",5),r(18),i(),t(19,"div",6),r(20,"Apparitions dans les recherches"),i()()()),o&2&&(s(8),x(m.profile_views),s(5),x(m.today_views),s(5),x(m.search_apperances))}});let n=e;return n})();var P=(()=>{let e=class e{constructor(a){this.http=a}getProfileOf(a){return{about:"Lorem ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing elitLorem ipsum dolor sit amet, consectetur adipiscing elit",banner_url:"",user_id:a.user_id}}};e.\u0275fac=function(o){return new(o||e)(V(B))},e.\u0275prov=N({token:e,factory:e.\u0275fac});let n=e;return n})();function ve(n,e){if(n&1){let l=w();r(0),t(1,"div",7)(2,"button",8),S("click",function(){E(l);let o=d();return _(o.expandable=!1)}),r(3,"Voir plus"),i()()}if(n&2){let l=d();v(" ",l.user_profile.about.slice(0,200),"... ")}}function ge(n,e){if(n&1){let l=w();r(0),t(1,"div",7)(2,"button",8),S("click",function(){E(l);let o=d();return _(o.expandable=!0)}),r(3,"Voir moins"),i()()}if(n&2){let l=d();v(" ",l.user_profile.about," ")}}var X=(()=>{let e=class e{constructor(a){this.profile=a,this.expandable=!1}ngOnInit(){this.user_profile=this.profile.getProfileOf(this.user),this.expandable=this.user_profile.about.length>100}};e.\u0275fac=function(o){return new(o||e)(f(P))},e.\u0275cmp=u({type:e,selectors:[["app-about-profile"]],inputs:{user:"user"},decls:9,vars:1,consts:[[1,"bg-white","shadow","rounded","overflow-hidden"],[1,"card-body","p-4"],[1,"flex","justify-between","mb-2"],[1,"card-title","text-2xl","font-bold"],[1,"text-xl","bg-transparent","hover:bg-gray-300","rounded-full","px-2","py-1"],[1,"bi","bi-pencil-square"],["class","card-actions"],[1,"card-actions"],[1,"ms-auto","link-primary",3,"click"]],template:function(o,m){o&1&&(t(0,"div",0)(1,"div",1)(2,"div",2)(3,"h2",3),r(4,"\xC0 propos"),i(),t(5,"button",4),p(6,"i",5),i()(),b(7,ve,4,1,"div",6)(8,ge,4,1),i()()),o&2&&(s(7),h(7,m.expandable?7:8))}});let n=e;return n})();function he(n,e){if(n&1&&(t(0,"div")(1,"p",9)(2,"strong"),r(3),i(),r(4," a republi\xE9 ceci \u2022 2 mois"),i(),t(5,"p")(6,"strong"),r(7,"Stefano Alletti"),i()(),t(8,"p",10),r(9,"Le langage le plus important n'est pas Python, C++, Rust, PHP ou Java. 99 % d'entre vous se tromperont \u{1F449} c'est l'\xE9criture. 1- L'\xE9criture d..."),i()(),p(10,"div",11),t(11,"div")(12,"p",9)(13,"strong"),r(14),i(),r(15," a republi\xE9 ceci \u2022 2 mois"),i(),t(16,"p")(17,"strong"),r(18,"Louiza KERDEL"),i()(),t(19,"p",10),r(20,"Je cherche un nouvel emploi : \u{1F4BC} 2900\u20AC net par mois \u{1F4C5} treizi\xE8me mois \u{1F48A} mutuelle et tickets restaurant \xE0 100% employeur \u{1F4C6} 8 sem..."),i()()),n&2){let l=d();s(3),x(l.user.username),s(11),x(l.user.username)}}function Se(n,e){if(n&1&&(t(0,"div")(1,"p")(2,"strong"),r(3),i(),r(4," a comment\xE9 un post \u2022 1 mois"),i(),t(5,"p",10),r(6,"Le jeu est formidable et vraiment ultra-realiste. bravo les gars !"),i()(),p(7,"div",11),t(8,"div")(9,"p")(10,"strong"),r(11),i(),r(12," a comment\xE9 un post \u2022 1 mois"),i(),t(13,"p",10),r(14,"Quand le stagiaire Bac+2 drop la database de production par erreur."),i()(),p(15,"div",11),t(16,"div")(17,"p")(18,"strong"),r(19),i(),r(20," a comment\xE9 un post \u2022 1 mois"),i(),t(21,"p",10),r(22,'La "possibilit\xE9" de r\xE9aliser un r\xEAve \u{1F480}'),i()()),n&2){let l=d();s(3),v(" ",l.user.username,""),s(8),v(" ",l.user.username,""),s(8),v(" ",l.user.username,"")}}var Y=(()=>{let e=class e{constructor(a){this.social=a,this.actualTab=F.POSTS,this.Tabs=F,this.Path=I}setTab(a){this.actualTab=a}};e.\u0275fac=function(o){return new(o||e)(f(y))},e.\u0275cmp=u({type:e,selectors:[["app-activity-profile"]],inputs:{user:"user"},decls:15,vars:4,consts:[[1,"p-3","bg-white","shadow","rounded-lg"],[1,"flex","justify-between","items-center","mb-3"],[1,"text-2xl","font-bold"],["role","tablist",1,"tabs","tabs-lifted"],["role","tab",1,"tab",3,"ngClass","click"],["role","tab",1,"tab","ml-4",3,"ngClass","click"],[1,"mt-4","text-sm"],[1,"flex"],[1,"btn","btn-link","p-0","mt-4","ms-auto","text-blue-500",3,"routerLink"],[1,"mb-2"],[1,"text-gray-600"],[1,"divider"]],template:function(o,m){o&1&&(t(0,"div",0)(1,"div",1)(2,"h2",2),r(3,"Activit\xE9"),i()(),t(4,"div",3)(5,"a",4),S("click",function(){return m.setTab(m.Tabs.POSTS)}),r(6,"Posts"),i(),t(7,"a",5),S("click",function(){return m.setTab(m.Tabs.COMMENTS)}),r(8,"Commentaires"),i()(),t(9,"div",6),b(10,he,21,2)(11,Se,23,3),i(),t(12,"div",7)(13,"a",8),r(14,"Voir plus de posts"),i()()()),o&2&&(s(5),c("ngClass",m.actualTab===m.Tabs.POSTS?"tab-active":""),s(2),c("ngClass",m.actualTab===m.Tabs.COMMENTS?"tab-active":""),s(3),h(10,m.actualTab===m.Tabs.POSTS?10:11),s(3),c("routerLink",m.Path.PROFILE_POSTS.toString()))},dependencies:[R,O]});let n=e;return n})(),F=function(n){return n[n.POSTS=0]="POSTS",n[n.COMMENTS=1]="COMMENTS",n}(F||{});var Z=(()=>{let e=class e{constructor(a){this.profile=a}};e.\u0275fac=function(o){return new(o||e)(f(P))},e.\u0275cmp=u({type:e,selectors:[["app-experience-profile"]],inputs:{user:"user"},decls:21,vars:1,consts:[[1,"p-4","bg-white","shadow","rounded-lg"],[1,"flex","justify-between","mb-4"],[1,"text-2xl","font-bold"],[1,"text-xl","bg-transparent","hover:bg-gray-300","rounded-full","px-2","py-1"],[1,"bi","bi-pencil-square"],[1,"mb-6"],[1,"flex","items-start","mb-4"],["priority","","width","40","height","40","alt","Company Logo",1,"mr-4","rounded-full",3,"ngSrc"],[1,"text-xl","font-semibold"],[1,"text-gray-500","text-sm"],[1,"text-blue-500"],[1,"mt-2","text-gray-700","text-sm"],[1,"divider"]],template:function(o,m){o&1&&(t(0,"div",0)(1,"div",1)(2,"h2",2),r(3,"Experience"),i(),t(4,"button",3),p(5,"i",4),i()(),t(6,"div",5)(7,"div",6),p(8,"img",7),t(9,"div")(10,"h3",8),r(11,"Freelance UX/UI designer"),i(),t(12,"p",9),r(13,"Freelance \u2022 Around the world"),i(),t(14,"p",9),r(15,"Jun 2016 \u2014 Present \u2022 "),t(16,"span",10),r(17,"3 ans 1 mois"),i()(),t(18,"p",11),r(19,"Work with clients and web studios as freelancer. Work in next areas: eCommerce web projects; creative landing pages; iOs and Android apps; corporate web sites and corporate identity sometimes."),i()()(),p(20,"div",12),i()()),o&2&&(s(8),c("ngSrc",m.user.image_url))},dependencies:[C]});let n=e;return n})();var ee=(()=>{let e=class e{constructor(){}};e.\u0275fac=function(o){return new(o||e)},e.\u0275cmp=u({type:e,selectors:[["app-education-profile"]],inputs:{user:"user"},decls:19,vars:1,consts:[[1,"p-4","bg-white","shadow","rounded-lg"],[1,"flex","justify-between","mb-4"],[1,"text-2xl","font-bold"],[1,"text-xl","bg-transparent","hover:bg-gray-300","rounded-full","px-2","py-1"],[1,"bi","bi-pencil-square"],[1,"mb-6"],[1,"flex","items-start","mb-4"],["priority","","width","40","height","40","alt","Company Logo",1,"mr-4","rounded-full",3,"ngSrc"],[1,"text-xl","font-semibold"],[1,"text-gray-500","text-sm"],[1,"text-blue-500"],[1,"divider"]],template:function(o,m){o&1&&(t(0,"div",0)(1,"div",1)(2,"h2",2),r(3,"\xC9ducation / Formations"),i(),t(4,"button",3),p(5,"i",4),i()(),t(6,"div",5)(7,"div",6),p(8,"img",7),t(9,"div")(10,"h3",8),r(11,"Nantes Universit\xE9"),i(),t(12,"p",9),r(13,"Master 1 (M1), Informatique \u2022 Architecture Logicielle"),i(),t(14,"p",9),r(15,"Juillet 2023 \u2014 Present \u2022 "),t(16,"span",10),r(17,"1 ans 1 mois"),i()()()(),p(18,"div",11),i()()),o&2&&(s(8),c("ngSrc",m.user.image_url))},dependencies:[C]});let n=e;return n})();var te=(()=>{let e=class e{constructor(){this.Path=I}};e.\u0275fac=function(o){return new(o||e)},e.\u0275cmp=u({type:e,selectors:[["app-skills-profile"]],inputs:{user:"user"},decls:18,vars:0,consts:[[1,"p-4","bg-white","shadow","rounded-lg"],[1,"flex","justify-between","items-center","mb-4"],[1,"text-2xl","font-bold"],[1,"text-xl","bg-transparent","hover:bg-gray-300","rounded-full","px-2","py-1"],[1,"bi","bi-pencil-square"],[1,"space-y-3"],[1,"flex"],[1,"btn","btn-link","p-0","mt-4","ms-auto","text-blue-500"]],template:function(o,m){o&1&&(t(0,"div",0)(1,"div",1)(2,"h2",2),r(3,"Comp\xE9tences"),i(),t(4,"button",3),p(5,"i",4),i()(),t(6,"ul",5)(7,"li"),r(8,"PostgreSQL"),i(),t(9,"li"),r(10,"ESLint"),i(),t(11,"li"),r(12,"Git"),i(),t(13,"li"),r(14,"Node.js"),i()(),t(15,"div",6)(16,"button",7),r(17,"Afficher tous les comp\xE9tences"),i()()())}});let n=e;return n})();function we(n,e){if(n&1&&(t(0,"div",2),p(1,"app-overview-profile",3)(2,"app-about-profile",3)(3,"app-activity-profile",3)(4,"app-experience-profile",3)(5,"app-education-profile",3)(6,"app-skills-profile",3),i(),t(7,"div",4),p(8,"app-stats-profile",3)(9,"app-users-suggestions",3),i()),n&2){let l=d();s(),c("user",l.user),s(),c("user",l.user),s(),c("user",l.user),s(),c("user",l.user),s(),c("user",l.user),s(),c("user",l.user),s(2),c("user",l.user),s(),c("user",l.user)}}var ie=(()=>{let e=class e{constructor(a){this.users=a}ngOnInit(){this.users.currentUser.subscribe(a=>{a&&(this.user=a)})}};e.\u0275fac=function(o){return new(o||e)(f(T))},e.\u0275cmp=u({type:e,selectors:[["app-profile"]],decls:3,vars:1,consts:[[1,"container","mx-4","my-5"],[1,"gap-4","grid","grid-cols-4","w-full"],[1,"col-span-3","flex","flex-col"],[1,"p-0","mb-4",3,"user"],[1,"col-span-1","flex","flex-col"]],template:function(o,m){o&1&&(t(0,"div",0)(1,"div",1),b(2,we,10,8),i()()),o&2&&(s(2),h(2,m.user?2:-1))},dependencies:[H,K,Q,X,Y,Z,ee,te]});let n=e;return n})();var Ie=[{path:"",component:ie}],ne=(()=>{let e=class e{};e.\u0275fac=function(o){return new(o||e)},e.\u0275mod=M({type:e}),e.\u0275inj=D({imports:[j.forChild(Ie),j]});let n=e;return n})();var nt=(()=>{let e=class e{};e.\u0275fac=function(o){return new(o||e)},e.\u0275mod=M({type:e}),e.\u0275inj=D({providers:[P],imports:[$,ne]});let n=e;return n})();export{nt as ProfileModule};
