import{a as A}from"./chunk-L2VLI5CZ.js";import{a as S,b as W,c as K}from"./chunk-OROPRCOW.js";import"./chunk-MJEQESGC.js";import{a as w}from"./chunk-XOKX2JYT.js";import{$ as x,A as U,B as k,Ba as P,D as L,Da as I,G as u,H as F,Ia as z,L as $,M as V,Oa as M,Pa as B,R as q,Ta as R,W as a,X as g,ba as d,fa as v,ga as y,gb as D,ha as C,ia as i,ib as j,ja as n,jb as E,ka as m,na as H,pa as T,qa as f,ra as J,ta as o,ua as c,va as h,wa as _}from"./chunk-GKZ3XEYF.js";function pe(t,e){return this.user.user_id}function me(t,e){if(t&1&&(i(0,"div",7)(1,"h5",8),o(2),n(),i(3,"p",9),o(4),n()()),t&2){let r=f().$implicit;a(2),_("",r.first_name," ",r.last_name,""),a(2),c(r.title?r.title:"Nouveau sur LinkedOut")}}function de(t,e){if(t&1&&(i(0,"div",7)(1,"h5",8),o(2),n(),i(3,"p",9),o(4),n()()),t&2){let r=f().$implicit;a(2),c(r.company_name),a(2),c(r.sector)}}function ce(t,e){if(t&1&&(i(0,"div")(1,"h5",8),o(2),n(),i(3,"p",9),o(4),n()()),t&2){let r=f().$implicit;a(2),_("",r.first_name," ",r.last_name,""),a(2),c(r.title?r.title:"Nouveau sur LinkedOut")}}function fe(t,e){t&1&&(i(0,"button",10),o(1,"Se connecter"),n())}function ue(t,e){t&1&&(i(0,"button",11),o(1,"Message"),n())}function xe(t,e){if(t&1&&(i(0,"div",2),m(1,"img",3),x(2,me,5,3,"div",4)(3,de,5,2)(4,ce,5,3),n(),i(5,"div",5),x(6,fe,2,0,"button",6)(7,ue,2,0),n()),t&2){let r=e.$implicit,s=f();a(),d("appSrc",r.imageName),a(),v(2,s.users.isJobseeker(r)?2:s.users.isCompany(r)?3:s.users.isAdmin(r)?4:-1),a(4),v(6,s.social.isConnection(s.user,r)?7:6)}}var G=(()=>{let e=class e{constructor(s,l){this.social=s,this.users=l,this.suggested_users=[]}ngOnInit(){this.users.suggestJobseekers().subscribe(s=>this.suggested_users=s)}};e.\u0275fac=function(l){return new(l||e)(g(A),g(w))},e.\u0275cmp=u({type:e,selectors:[["app-users-suggestions"]],inputs:{user:"user"},decls:5,vars:0,consts:[[1,"rounded-lg","bg-white","shadow","p-4"],[1,"font-semibold","text-xl","mb-6"],[1,"flex","items-center","my-2"],["width","64","height","64","alt","Profile pic",1,"w-10","h-10","rounded-full","mr-3",3,"appSrc"],["class","w-full"],[1,"w-full","text-end","mb-4"],["type","button","class","btn btn-outline btn-primary btn-sm"],[1,"w-full"],[1,"text-sm"],[1,"text-xs","text-gray-500"],["type","button",1,"btn","btn-outline","btn-primary","btn-sm"],["type","button",1,"btn","btn-primary","btn-sm"]],template:function(l,p){l&1&&(i(0,"div",0)(1,"h1",1),o(2,"Autres profils similaires"),n(),y(3,xe,8,3,null,null,pe,!0),n()),l&2&&(a(3),C(p.suggested_users))},dependencies:[S]});let t=e;return t})();function ge(t,e){if(t&1&&(i(0,"div",11)(1,"h2",12),o(2),n(),i(3,"div",13),m(4,"i",14),o(5),n()(),i(6,"p",15),o(7),n()),t&2){let r=f();a(2),_("",r.user.first_name," ",r.user.last_name,""),a(3),h(" ",r.user.address," "),a(2),c(r.user.title)}}function he(t,e){if(t&1&&(i(0,"div",11)(1,"h2",16),o(2),n(),i(3,"div",13),m(4,"i",17),o(5),n()(),i(6,"p",18),o(7),n(),i(8,"div",19),m(9,"i",20),i(10,"a",21),o(11),n()()),t&2){let r=f();a(2),c(r.user.company_name),a(3),h(" ",r.user.headquarters," "),a(2),c(r.user.sector),a(3),J("href","https://",r.user.website,"",q),a(),c(r.user.website)}}function Se(t,e){if(t&1&&(i(0,"div",11)(1,"h2",16),o(2),i(3,"span",22),o(4,"staff"),n()(),i(5,"div",13),m(6,"i",23),o(7),n()(),i(8,"p",18),o(9),n()),t&2){let r=f();a(2),_("",r.user.first_name," ",r.user.last_name," "),a(5),h(" ",r.user.email," "),a(2),c(r.user.title)}}var Q=(()=>{let e=class e{constructor(s,l){this.users=s,this.social=l,this.Path=E}};e.\u0275fac=function(l){return new(l||e)(g(w),g(A))},e.\u0275cmp=u({type:e,selectors:[["app-header-profile"]],inputs:{user:"user"},decls:17,vars:5,consts:[[1,"bg-white","shadow","rounded","overflow-hidden"],[1,"relative","mb-2"],["alt","Banner Image","height","396","width","1584",1,"w-full","h-32","object-cover",3,"appSrc"],[1,"rounded-full","border-4","border-white","absolute","left-5","transform","-bottom-16"],[1,"w-24","h-22","bg-white","rounded-full","flex","items-start","justify-start"],["alt","Profile Image","height","96","width","96",1,"w-full","h-full","rounded-full",3,"appSrc"],[1,"absolute","top-0","right-0","m-2"],[1,"text-white","text-xl","bg-transparent","hover:bg-gray-500","rounded-full","px-2","py-1"],[1,"bi","bi-pencil-square"],[1,"p-3","mx-2"],[1,"link","link-primary","mt-2",3,"routerLink"],[1,"flex","items-center","justify-between"],[1,"text-2xl","font-bold"],[1,"me-3"],[1,"bi","bi-geo-alt-fill","text-primary","text-lg"],[1,"text-gray-600","text-sm"],[1,"text-xl","font-bold"],[1,"bi","bi-geo-alt-fill","text-primary","text-lg","me-2"],[1,"text-gray-600"],[1,"text-sm","text-gray-600","flex","items-center"],[1,"bi","bi-box-arrow-up-right","text-lg","me-2"],["target","_blank",1,"link","link-primary",3,"href"],[1,"badge","badge-info"],[1,"bi","bi-envelope-fill","color-primary","text-lg","me-2"]],template:function(l,p){l&1&&(i(0,"div",0)(1,"div",1),m(2,"img",2),i(3,"div",3)(4,"div",4),m(5,"img",5),n()(),i(6,"div",6)(7,"button",7),m(8,"i",8),n()()(),m(9,"br")(10,"br"),i(11,"div",9),x(12,ge,8,4)(13,he,12,5)(14,Se,10,4),i(15,"a",10),o(16),n()()()),l&2&&(a(2),d("appSrc",p.user.profile.banner_name),a(3),d("appSrc",p.user.imageName),a(7),v(12,p.users.isJobseeker(p.user)?12:p.users.isCompany(p.user)?13:p.users.isAdmin(p.user)?14:-1),a(3),d("routerLink",p.Path.NETWORK.toString()),a(),h("",p.user.profile.connections," connections"))},dependencies:[D,S]});let t=e;return t})();var Y=(()=>{let e=class e{constructor(){}ngOnInit(){}};e.\u0275fac=function(l){return new(l||e)},e.\u0275cmp=u({type:e,selectors:[["app-stats-profile"]],inputs:{profile:"profile"},decls:21,vars:3,consts:[[1,"p-4","bg-white","shadow","rounded-lg"],[1,"font-semibold","text-xl"],[1,"text-gray-500","text-sm","mb-3"],[1,"bi","bi-eye-fill","me-2"],[1,"my-4"],[1,"text-5xl","font-bold","text-primary"],[1,"text-gray-500"]],template:function(l,p){l&1&&(i(0,"div",0)(1,"h1",1),o(2,"Tableau de bord"),n(),i(3,"span",2),m(4,"i",3),o(5,"Priv\xE9 pour vous "),n(),i(6,"div",4)(7,"div",5),o(8),n(),i(9,"div",6),o(10,"Vues totales"),n()(),i(11,"div",4)(12,"div",5),o(13),n(),i(14,"div",6),o(15,"Connexions"),n()(),i(16,"div",4)(17,"div",5),o(18),n(),i(19,"div",6),o(20,"Apparitions dans les recherches"),n()()()),l&2&&(a(8),c(p.profile.connections),a(5),c(p.profile.connections),a(5),c(0))}});let t=e;return t})();function _e(t,e){if(t&1){let r=H();o(0),i(1,"div",7)(2,"button",8),T("click",function(){$(r);let l=f();return V(l.expandable=!1)}),o(3,"Voir plus"),n()()}if(t&2){let r=f();h(" ",r.profile.bio.slice(0,200),"... ")}}function Ee(t,e){if(t&1&&o(0),t&2){let r=f();h(" ",r.profile.bio," ")}}var Z=(()=>{let e=class e{constructor(){this.expandable=!1}ngOnInit(){this.expandable=this.profile.bio.length>100}};e.\u0275fac=function(l){return new(l||e)},e.\u0275cmp=u({type:e,selectors:[["app-about-profile"]],inputs:{profile:"profile"},decls:9,vars:1,consts:[[1,"bg-white","shadow","rounded","overflow-hidden"],[1,"card-body","p-4"],[1,"flex","justify-between","mb-2"],[1,"card-title","text-2xl","font-bold"],[1,"text-xl","bg-transparent","hover:bg-gray-300","rounded-full","px-2","py-1"],[1,"bi","bi-pencil-square"],["class","card-actions"],[1,"card-actions"],[1,"ms-auto","link-primary",3,"click"]],template:function(l,p){l&1&&(i(0,"div",0)(1,"div",1)(2,"div",2)(3,"h2",3),o(4,"\xC0 propos"),n(),i(5,"button",4),m(6,"i",5),n()(),x(7,_e,4,1,"div",6)(8,Ee,1,1),n()()),l&2&&(a(7),v(7,p.expandable?7:8))}});let t=e;return t})();var Pe=(t,e)=>e.post_id;function Ie(t,e){if(t&1&&(i(0,"div")(1,"p",9)(2,"strong"),o(3,"Vous"),n(),o(4," avez publi\xE9 ceci \u2022 "),m(5,"span",10),n(),i(6,"p",11),o(7),n()(),m(8,"div",12)),t&2){let r=e.$implicit;a(5),d("appDateFormat",r.created),a(2),c(r.description)}}function ke(t,e){t&1&&(i(0,"p",13),o(1,"Aucun post trouv\xE9"),n())}function Fe(t,e){if(t&1&&(y(0,Ie,9,2,null,null,Pe,!1,ke,2,0,"p",14),P(3,"slice")),t&2){let r=f();C(I(3,1,r.profile.posts,0,2))}}var Me=(t,e)=>e.comment_id;function De(t,e){if(t&1&&(i(0,"div")(1,"p",9)(2,"strong"),o(3,"Vous"),n(),o(4," avez comment\xE9 ceci \u2022 "),m(5,"span",10),n(),i(6,"p",11),o(7),n()(),m(8,"div",12)),t&2){let r=e.$implicit;a(5),d("appDateFormat",r.created_at),a(2),c(r.content)}}function Ae(t,e){t&1&&(i(0,"p",13),o(1,"Aucun commentaire trouv\xE9"),n())}function je(t,e){if(t&1&&(y(0,De,9,2,null,null,Me,!1,Ae,2,0,"p",14),P(3,"slice")),t&2){let r=f();C(I(3,1,r.profile.comments,0,3))}}var ee=(()=>{let e=class e{constructor(){this.actualTab=N.POSTS,this.Tabs=N,this.Path=E}setTab(s){this.actualTab=s}};e.\u0275fac=function(l){return new(l||e)},e.\u0275cmp=u({type:e,selectors:[["app-activity-profile"]],inputs:{profile:"profile"},decls:15,vars:4,consts:[[1,"p-3","bg-white","shadow","rounded-lg"],[1,"flex","justify-between","items-center","mb-3"],[1,"text-2xl","font-bold"],["role","tablist",1,"tabs","tabs-lifted"],["role","tab",1,"tab",3,"ngClass","click"],["role","tab",1,"tab","ml-4",3,"ngClass","click"],[1,"mt-4","text-sm"],[1,"flex"],[1,"btn","btn-link","p-0","mt-4","ms-auto","text-blue-500",3,"routerLink"],[1,"mb-2"],[3,"appDateFormat"],[1,"text-gray-600"],[1,"divider"],[1,"text-gray-600","text-center","my-2"],["class","text-gray-600 text-center my-2"]],template:function(l,p){l&1&&(i(0,"div",0)(1,"div",1)(2,"h2",2),o(3,"Activit\xE9"),n()(),i(4,"div",3)(5,"a",4),T("click",function(){return p.setTab(p.Tabs.POSTS)}),o(6,"Posts"),n(),i(7,"a",5),T("click",function(){return p.setTab(p.Tabs.COMMENTS)}),o(8,"Commentaires"),n()(),i(9,"div",6),x(10,Fe,4,5)(11,je,4,5),n(),i(12,"div",7)(13,"a",8),o(14,"Voir plus de posts"),n()()()),l&2&&(a(5),d("ngClass",p.actualTab===p.Tabs.POSTS?"tab-active":""),a(2),d("ngClass",p.actualTab===p.Tabs.COMMENTS?"tab-active":""),a(3),v(10,p.actualTab===p.Tabs.POSTS?10:11),a(3),d("routerLink",p.Path.PROFILE_POSTS.toString()))},dependencies:[z,D,W,M]});let t=e;return t})(),N=function(t){return t[t.POSTS=0]="POSTS",t[t.COMMENTS=1]="COMMENTS",t}(N||{});var te=(()=>{let e=class e{constructor(){}};e.\u0275fac=function(l){return new(l||e)},e.\u0275cmp=u({type:e,selectors:[["app-experience-profile"]],inputs:{profile:"profile"},decls:21,vars:1,consts:[[1,"p-4","bg-white","shadow","rounded-lg"],[1,"flex","justify-between","mb-4"],[1,"text-2xl","font-bold"],[1,"text-xl","bg-transparent","hover:bg-gray-300","rounded-full","px-2","py-1"],[1,"bi","bi-pencil-square"],[1,"mb-6"],[1,"flex","items-start","mb-4"],["width","40","height","40","alt","Company Logo",1,"mr-4","rounded-full",3,"appSrc"],[1,"text-xl","font-semibold"],[1,"text-gray-500","text-sm"],[1,"text-blue-500"],[1,"mt-2","text-gray-700","text-sm"],[1,"divider"]],template:function(l,p){l&1&&(i(0,"div",0)(1,"div",1)(2,"h2",2),o(3,"Experiences"),n(),i(4,"button",3),m(5,"i",4),n()(),i(6,"div",5)(7,"div",6),m(8,"img",7),i(9,"div")(10,"h3",8),o(11,"Freelance UX/UI designer"),n(),i(12,"p",9),o(13,"Freelance \u2022 Around the world"),n(),i(14,"p",9),o(15,"Jun 2016 \u2014 Present \u2022 "),i(16,"span",10),o(17,"3 ans 1 mois"),n()(),i(18,"p",11),o(19,"Work with clients and web studios as freelancer. Work in next areas: eCommerce web projects; creative landing pages; iOs and Android apps; corporate web sites and corporate identity sometimes."),n()()(),m(20,"div",12),n()()),l&2&&(a(8),d("appSrc",p.profile.banner_name))},dependencies:[S]});let t=e;return t})();var ie=(()=>{let e=class e{constructor(){}};e.\u0275fac=function(l){return new(l||e)},e.\u0275cmp=u({type:e,selectors:[["app-education-profile"]],inputs:{profile:"profile"},decls:19,vars:1,consts:[[1,"p-4","bg-white","shadow","rounded-lg"],[1,"flex","justify-between","mb-4"],[1,"text-2xl","font-bold"],[1,"text-xl","bg-transparent","hover:bg-gray-300","rounded-full","px-2","py-1"],[1,"bi","bi-pencil-square"],[1,"mb-6"],[1,"flex","items-start","mb-4"],["width","40","height","40","alt","Company Logo",1,"mr-4","rounded-full",3,"appSrc"],[1,"text-xl","font-semibold"],[1,"text-gray-500","text-sm"],[1,"text-blue-500"],[1,"divider"]],template:function(l,p){l&1&&(i(0,"div",0)(1,"div",1)(2,"h2",2),o(3,"\xC9ducation / Formations"),n(),i(4,"button",3),m(5,"i",4),n()(),i(6,"div",5)(7,"div",6),m(8,"img",7),i(9,"div")(10,"h3",8),o(11,"Nantes Universit\xE9"),n(),i(12,"p",9),o(13,"Master 1 (M1), Informatique \u2022 Architecture Logicielle"),n(),i(14,"p",9),o(15,"Juillet 2023 \u2014 Present \u2022 "),i(16,"span",10),o(17,"1 ans 1 mois"),n()()()(),m(18,"div",11),n()()),l&2&&(a(8),d("appSrc",p.profile.banner_name))},dependencies:[S]});let t=e;return t})();var Le=(t,e)=>e.skill_id;function $e(t,e){if(t&1&&(i(0,"li"),o(1),n()),t&2){let r=e.$implicit;a(),c(r.name)}}var ne=(()=>{let e=class e{constructor(){this.Path=E}};e.\u0275fac=function(l){return new(l||e)},e.\u0275cmp=u({type:e,selectors:[["app-skills-profile"]],inputs:{profile:"profile"},decls:13,vars:4,consts:[[1,"p-4","bg-white","shadow","rounded-lg"],[1,"flex","justify-between","items-center","mb-4"],[1,"text-2xl","font-bold"],[1,"text-xl","bg-transparent","hover:bg-gray-300","rounded-full","px-2","py-1"],[1,"bi","bi-pencil-square"],[1,"space-y-3"],[1,"flex"],[1,"btn","btn-link","p-0","mt-4","ms-auto","text-blue-500"]],template:function(l,p){l&1&&(i(0,"div",0)(1,"div",1)(2,"h2",2),o(3,"Comp\xE9tences"),n(),i(4,"button",3),m(5,"i",4),n()(),i(6,"ul",5),y(7,$e,2,1,"li",null,Le),P(9,"slice"),n(),i(10,"div",6)(11,"button",7),o(12,"Afficher tous les comp\xE9tences"),n()()()),l&2&&(a(7),C(I(9,0,p.profile.skills,0,5)))},dependencies:[M]});let t=e;return t})();function qe(t,e){if(t&1&&m(0,"app-experience-profile",4)(1,"app-education-profile",4)(2,"app-skills-profile",4),t&2){let r=f(2);d("profile",r.user.profile),a(),d("profile",r.user.profile),a(),d("profile",r.user.profile)}}function He(t,e){if(t&1&&(i(0,"div",2),m(1,"app-header-profile",3)(2,"app-about-profile",4)(3,"app-activity-profile",4),x(4,qe,3,3),n(),i(5,"div",5),m(6,"app-stats-profile",4)(7,"app-users-suggestions",3),n()),t&2){let r=f();a(),d("user",r.user),a(),d("profile",r.user.profile),a(),d("profile",r.user.profile),a(),v(4,r.users.isJobseeker(r.user)?4:-1),a(2),d("profile",r.user.profile),a(),d("user",r.user)}}var oe=(()=>{let e=class e{constructor(s){this.users=s}ngOnInit(){this.users.currentUser.subscribe(s=>{s&&(this.user=s)})}};e.\u0275fac=function(l){return new(l||e)(g(w))},e.\u0275cmp=u({type:e,selectors:[["app-profile"]],decls:3,vars:1,consts:[[1,"mx-4","my-5"],[1,"gap-4","grid","grid-cols-4","w-full"],[1,"col-span-3","flex","flex-col"],[1,"p-0","mb-4",3,"user"],[1,"p-0","mb-4",3,"profile"],[1,"col-span-1","flex","flex-col"]],template:function(l,p){l&1&&(i(0,"div",0)(1,"div",1),x(2,He,8,6),n()()),l&2&&(a(2),v(2,p.user?2:-1))},dependencies:[G,Q,Y,Z,ee,te,ie,ne]});let t=e;return t})();var Je=[{path:"",component:oe}],re=(()=>{let e=class e{};e.\u0275fac=function(l){return new(l||e)},e.\u0275mod=F({type:e}),e.\u0275inj=k({imports:[j.forChild(Je),j]});let t=e;return t})();var ae=(()=>{let e=class e{constructor(s){this.http=s}};e.\u0275fac=function(l){return new(l||e)(L(R))},e.\u0275prov=U({token:e,factory:e.\u0275fac});let t=e;return t})();var _t=(()=>{let e=class e{};e.\u0275fac=function(l){return new(l||e)},e.\u0275mod=F({type:e}),e.\u0275inj=k({providers:[ae],imports:[B,re,K]});let t=e;return t})();export{_t as ProfileModule};
