$(function() {
	
	
	$.post('menu.html', function(datas) {
        if(datas) {
        	
        	var data = eval(datas);
        	
        	for(var index=0;index<data.length;index++){
        		var item = data[index];
	    		  var selected = false;
	              if (index == 0) {//选中的一个
	                  selected = true;
	              }
	              
	              // Accordion 折叠面板
	              $('#menuAccordion').accordion('add', {
	                  title: item.text,
	                  content: "<ul id='menu_tree_" + item.id + "'></ul>",
	                  selected: selected,
	                  open:item.open
	              });
	              
	              
	              // 树形菜单
	              var treeId = '#menu_tree_' + item.id;
	              $('#menu_tree_' + item.id).tree({
	                  data: item.children,
	                  onClick: function(node) {//绑定点击事件
	                      if (node.children!=null && node.children.length > 0) {
	                          return;
	                      }
	                      
	                      // 添加选项卡
	                      addTab(node.text,node.url);
	                  }//,iconCls:item.icon
	              });
	              
        	}
        	
          /*  $.each(data, function(index, item) {
                var selected = false;
                if (index == 0) {//选中的一个
                    selected = true;
                }
                
                // Accordion 折叠面板
                $('#menuAccordion').accordion('add', {
                    title: item.text,
                    content: "<ul id='menu_tree_" + item.id + "'></ul>",
                    selected: selected
                });
                
                // 树形菜单
                $('#menu_tree_' + item.id).tree({
                    data: item.children,
                    onClick: function(node) {//绑定点击事件
                        if (node.children.length != 0) {
                            return;
                        }
                        // 添加选项卡
                        addTab('tabs', node.text, node.url);
                    }
                });
            });*/
        }
        
        
        
        
        
    }, 'json');
	
	
	$('.easyui-accordion li a').click(
					
			function() {
				var tabTitle = $(this).text();
				var url = $(this).attr("href");
				addTab(tabTitle, url);
				$('.easyui-accordion li div').removeClass("selected");
				$(this).parent().addClass("selected");
				})
				//绑定hover
				.hover(
					function() {
						$(this).parent().addClass("hover");
					}, function() {
						$(this).parent().removeClass("hover");
					}
				);
		
		//添加 Tab
		function addTab(subtitle, url) {
			if (!$('#tabs').tabs('exists', subtitle)) {
				$('#tabs').tabs('add', {
					title : subtitle,
					content : createFrame(url),
					closable : true,
					width : $('#mainPanle').width() - 10,
					height : $('#mainPanle').height() - 26
				});
			} else {
				$('#tabs').tabs('select', subtitle);
			}
			tabClose();
		}
		
		//创建 frame
		function createFrame(url) {
			var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0" src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';
			return s;
		}
		
		//关闭 Tab
		function tabClose() {
			/*双击关闭TAB选项卡*/
			$(".tabs-inner").dblclick(function() {
				var subtitle = $(this).children("span").text();
				$('#tabs').tabs('close', subtitle);
			})
			$(".tabs-inner").bind('contextmenu', function(e) {
				$('#mm').menu('show', {
					left : e.pageX,
					top : e.pageY,
				});
				var subtitle = $(this).children("span").text();
				$('#mm').data("currtab", subtitle);
				return false;
			});
		}
		
		//绑定右键菜单事件
		//关闭当前
		$('#mm-tabclose').click(function() { 
			var currtab_title = $('#mm').data("currtab");
			$('#tabs').tabs('close', currtab_title);
		});
		
		//全部关闭
		$('#mm-tabcloseall').click(function() {
			$('.tabs-inner span').each(function(i, n) {
				var t = $(n).text();
				$('#tabs').tabs('close', t);
			});
		});
		//关闭除当前之外的TAB
		$('#mm-tabcloseother').click(function() {
			var currtab_title = $('#mm').data("currtab");
			$('.tabs-inner span').each(function(i, n) {
				var t = $(n).text();
				if (t != currtab_title)
					$('#tabs').tabs('close', t);
			});
		});
		//关闭当前右侧的TAB
		$('#mm-tabcloseright').click(function() {
			var nextall = $('.tabs-selected').nextAll();
			if (nextall.length == 0) {
				//msgShow('系统提示','后边没有啦~~','error');
				alert('后边没有啦~~');
				return false;
			}
			nextall.each(function(i, n) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			});
			return false;
		});
		//关闭当前左侧的TAB
		$('#mm-tabcloseleft').click(function() {
			var prevall = $('.tabs-selected').prevAll();
			if (prevall.length == 0) {
				alert('到头了，前边没有啦~~');
				return false;
			}
			prevall.each(function(i, n) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			});
			return false;
		});
		//退出
		$("#mm-exit").click(function() {
			$('#mm').menu('hide');
		});
		
		
		
		
		
		// 初始化 左侧菜单
	    $('#menuAccordion').accordion({
	        fillSpace: true,
	        fit: true,
	        border: false,
	        animate: false
	    });
	    
	    
	    
});