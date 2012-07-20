#set($layout="main.vm")
#set($page_title = "NextApp 相关案例")
<div id="page_banner">
    <div class="container">
        <h2>NextApp 相关案例</h2>
    </div>
</div>
<div id="main">
    <div class="container clearfix">
        <div class="cases_list">
            <ul>
				#foreach($appid in [3,4,5,1,2])
				#set($app = $NextApp.app($appid))
                <li>
                    <div class="item">
                        <a href="${app.home_url}" class="site" target="_blank">
							<span class="pic"><img src="$link.upload("img/${app.logo}")" title="$format.text(${app.name})"/></span>
							<span>$format.text($format.abbreviate(${app.outline},64))</span>
						</a>
                        <p class="download"><strong>下载：</strong>										
                			#foreach($ver in $app.versions())
                			<a href="$ver.url()">$resource.ui("s_client_type_${ver.client_type}")</a>#if($velocityHasNext) /#end
            				#end
						</p>
					</div>
                </li>
				#end
            </ul>
        </div>
    </div>
</div>
<script type='text/javascript'>
<!--
    (function ($) {
        $(".cases_list ul li").hover(function () {
            $(this).addClass('on');
        }, function () {
            $(this).removeClass('on');
        });
    })(jQuery);
//-->
</script>                                                                                                                                                                                     �       � @ A   B    C D       E   
                                                                                                                                                                                                                                                                                                                                                                                                                                                      