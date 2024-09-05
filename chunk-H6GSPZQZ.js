import{a as I,d as M}from"./chunk-63WYLOOZ.js";import{$ as f,C as v,Cb as h,D as d,Fa as n,Ga as S,Ha as g,I as s,J as u,_ as c,db as y,ga as E,oa as i,pa as e,qa as r,tb as C,ub as P,wa as b,wb as w,xb as A,yb as x}from"./chunk-CSVQ67SG.js";var N=(()=>{let t=class t{constructor(l){this.users=l,this.Path=h}ngOnInit(){this.users.currentUser.subscribe(l=>{l&&(this.user=l)})}onOutletLoaded(l){l.user=this.user}};t.\u0275fac=function(m){return new(m||t)(f(I))},t.\u0275cmp=s({type:t,selectors:[["app-settings"]],decls:21,vars:3,consts:[[1,"gap-0","grid","grid-cols-4","w-full"],[1,"col-span-1","flex","flex-col","w-full","p-4","bg-white","h-screen","shadow"],[1,"text-3xl","font-bold","my-4","ms-3"],[1,"border-gray-200","mb-6"],[1,"menu","space-y-4","text-lg"],[1,"mb-2"],["routerLinkActive","border-l-4",1,"border-primary","rounded-none",3,"routerLink"],[1,"bi","bi-person-fill","me-2"],[1,"bi","bi-lock-fill","me-2"],[1,"bi","bi-bell-fill","me-2"],[1,"col-span-3","flex","flex-col"],[1,"w-4/5","p-8","mx-auto"],[3,"activate"]],template:function(m,a){m&1&&(i(0,"div",0)(1,"div",1)(2,"h1",2),n(3,"Pr\xE9ferences"),e(),r(4,"hr",3),i(5,"ul",4)(6,"li",5)(7,"a",6),r(8,"i",7),n(9,"Pr\xE9f\xE9rences du compte"),e()(),i(10,"li",5)(11,"a",6),r(12,"i",8),n(13,"Authentification et S\xE9curit\xE9"),e()(),i(14,"li",5)(15,"a",6),r(16,"i",9),n(17,"Notifications"),e()()()(),i(18,"div",10)(19,"div",11)(20,"router-outlet",12),b("activate",function(k){return a.onOutletLoaded(k)}),e()()()()),m&2&&(c(7),E("routerLink",a.Path.PREFERENCES_ACCOUNT.toString()),c(4),E("routerLink",a.Path.PREFERENCES_SECURITY.toString()),c(4),E("routerLink",a.Path.PREFERENCES_NOTIFICATIONS.toString()))},dependencies:[P,w,A]});let o=t;return o})();var F=(()=>{let t=class t{constructor(){}};t.\u0275fac=function(m){return new(m||t)},t.\u0275cmp=s({type:t,selectors:[["app-account-settings"]],decls:61,vars:0,consts:[[1,"bg-white","rounded-lg","shadow","p-6"],[1,"text-xl","font-bold","mb-4"],["type","button"],[1,"flex","justify-between","items-center","mb-2"],[1,"bi","bi-chevron-right"],[1,"my-6"],[1,"text-xl","font-bold","mt-6","mb-4"],[1,"flex","items-center"],[1,"mr-2","text-sm","text-gray-500"]],template:function(m,a){m&1&&(i(0,"div",0)(1,"h2",1),n(2,"Informations de profil"),e(),i(3,"ul")(4,"button",2)(5,"li",3)(6,"span"),n(7,"Nom, lieu et secteur"),e(),r(8,"i",4),e()()(),r(9,"hr",5),i(10,"h2",6),n(11,"Pr\xE9f\xE9rences g\xE9n\xE9rales"),e(),i(12,"ul")(13,"a")(14,"li",3)(15,"span"),n(16,"Lancer les vid\xE9os automatiquement"),e(),i(17,"div",7)(18,"span",8),n(19,"Activ\xE9"),e(),r(20,"i",4),e()()(),i(21,"a")(22,"li",3)(23,"span"),n(24,"Effets sonores"),e(),i(25,"div",7)(26,"span",8),n(27,"Activ\xE9"),e(),r(28,"i",4),e()()(),i(29,"a")(30,"li",3)(31,"span"),n(32,"Affichage des photos de profil"),e(),i(33,"div",7)(34,"span",8),n(35,"Tous les membres de LinkedIn"),e(),r(36,"i",4),e()()(),i(37,"a")(38,"li",3)(39,"span"),n(40,"Pr\xE9f\xE9rences du fil d'actualit\xE9"),e(),r(41,"i",4),e()(),i(42,"a")(43,"li",3)(44,"span"),n(45,"Personnes que vous ne suivez plus"),e(),r(46,"i",4),e()()(),r(47,"hr",5),i(48,"h2",6),n(49,"Gestion du compte"),e(),i(50,"ul")(51,"a")(52,"li",3)(53,"span"),n(54,"D\xE9sactiver temporairement le compte"),e(),r(55,"i",4),e()(),i(56,"a")(57,"li",3)(58,"span"),n(59,"Fermer le compte"),e(),r(60,"i",4),e()()()())}});let o=t;return o})();var R=(()=>{let t=class t{constructor(l){this.route=l,this.Path=h,this.route.params.subscribe(m=>{this.route.snapshot.data.user&&(this.user=this.route.snapshot.data.user)})}};t.\u0275fac=function(m){return new(m||t)(f(C))},t.\u0275cmp=s({type:t,selectors:[["app-security-settings"]],decls:35,vars:2,consts:[[1,"bg-white","rounded-lg","shadow","p-6"],[1,"text-xl","font-bold","mb-4"],[1,"flex","justify-between","items-center","mb-3"],[1,"flex","items-center"],[1,"mr-2","text-sm","text-gray-500"],[1,"bi","bi-chevron-right"]],template:function(m,a){m&1&&(i(0,"div",0)(1,"h2",1),n(2,"Acc\xE8s au compte"),e(),i(3,"ul")(4,"a")(5,"li",2)(6,"span"),n(7,"Addresse e-mail"),e(),i(8,"div",3)(9,"span",4),n(10),e(),r(11,"i",5),e()()(),i(12,"a")(13,"li",2)(14,"span"),n(15,"Numeros de t\xE9l\xE9phone"),e(),r(16,"i",5),e()(),i(17,"a")(18,"li",2)(19,"span"),n(20,"Changer le mot de passe"),e(),r(21,"i",5),e()(),i(22,"a")(23,"li",2)(24,"span"),n(25,"O\xF9 vous vous \xEAtes identifi\xE9"),e(),r(26,"i",5),e()(),i(27,"a")(28,"li",2)(29,"span"),n(30,"Verifications en deux \xE9tapes"),e(),i(31,"div",3)(32,"span",4),n(33),e(),r(34,"i",5),e()()()()()),m&2&&(c(10),g("",a.user.email," "),c(23),S(a.user.using_mfa?"Activ\xE9":"D\xE9sactiv\xE9"))}});let o=t;return o})();var j=(()=>{let t=class t{};t.\u0275fac=function(m){return new(m||t)},t.\u0275cmp=s({type:t,selectors:[["app-notifications-settings"]],decls:34,vars:0,consts:[[1,"bg-white","rounded-lg","shadow","p-6"],[1,"text-xl","font-bold","mb-4"],[1,"flex","justify-between","items-center","mb-3"],[1,"bi","bi-chevron-right"]],template:function(m,a){m&1&&(i(0,"div",0)(1,"h2",1),n(2,"Acc\xE8s au compte"),e(),i(3,"ul")(4,"a")(5,"li",2)(6,"span"),n(7,"Recherche d\u2019un emploi"),e(),r(8,"i",3),e()(),i(9,"a")(10,"li",2)(11,"span"),n(12,"Recruter quelqu\u2019un"),e(),r(13,"i",3),e()(),i(14,"a")(15,"li",2)(16,"span"),n(17,"Connexion avec d\u2019autres personnes"),e(),r(18,"i",3),e()(),i(19,"a")(20,"li",2)(21,"span"),n(22,"Publication et commentaires"),e(),r(23,"i",3),e()(),i(24,"a")(25,"li",2)(26,"span"),n(27,"Messagerie"),e(),r(28,"i",3),e()(),i(29,"a")(30,"li",2)(31,"span"),n(32,"Mise \xE0 jour de votre profil"),e(),r(33,"i",3),e()()()())}});let o=t;return o})();var O=[{path:"",component:N,children:[{path:"",redirectTo:"account",pathMatch:"full"},{path:"account",component:F},{path:"security",component:R},{path:"notifications",component:j}]}],L=(()=>{let t=class t{};t.\u0275fac=function(m){return new(m||t)},t.\u0275mod=u({type:t}),t.\u0275inj=d({imports:[x.forChild(O),x]});let o=t;return o})();var T=(()=>{let t=class t{constructor(){}};t.\u0275fac=function(m){return new(m||t)},t.\u0275prov=v({token:t,factory:t.\u0275fac});let o=t;return o})();var pe=(()=>{let t=class t{};t.\u0275fac=function(m){return new(m||t)},t.\u0275mod=u({type:t}),t.\u0275inj=d({providers:[T],imports:[y,M,L]});let o=t;return o})();export{pe as PreferencesModule};
